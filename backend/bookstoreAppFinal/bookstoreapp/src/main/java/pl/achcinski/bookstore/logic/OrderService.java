package pl.achcinski.bookstore.logic;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.achcinski.bookstore.model.Book;
import pl.achcinski.bookstore.model.CartItem;
import pl.achcinski.bookstore.repository.CartItemRepository;
import pl.achcinski.bookstore.model.Order;
import pl.achcinski.bookstore.repository.OrderRepository;
import pl.achcinski.bookstore.model.ShippingInformation;
import pl.achcinski.bookstore.repository.ShippingInformationRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    final OrderRepository orderRepository;
    final ShippingInformationRepository shippingInformationRepository;
    final CartItemRepository cartItemRepository;
    final ShoppingCartService shoppingCartService;
    final MailService mailService;
    final JavaMailSender javaMailSender;

    public String getCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


    public ShippingInformation loadShippingInfo() {
        ShippingInformation shippingInformation;
        if (!shippingInformationRepository.existsByCustomerId(getCustomerId())) {
            shippingInformation = new ShippingInformation();
        } else {
            shippingInformation = shippingInformationRepository.findByCustomerId(getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + getCustomerId()));
        }

        return shippingInformation;
    }

    public List<String> getUserBooksToOrder() {
        List<CartItem> cartItemList = cartItemRepository.findByCustomerId(getCustomerId());
        List<Book> bookList = cartItemList.stream().map(CartItem::getBook).collect(Collectors.toList());
        return bookList.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    public void createOrderAndSendMails(HttpServletRequest request) throws MessagingException {
        List<String> bookNameList = getUserBooksToOrder();
        StringBuilder sb = new StringBuilder();
        for (String s : bookNameList) {
            sb.append(s);
            sb.append(", ");
        }
        Order order = new Order();
        order.setPrice(shoppingCartService.getAmountTotal(getCustomerId()));
        order.setCustomerId(getCustomerId());
        order.setBoughtBooks(sb.toString());
        orderRepository.save(order);

        KeycloakAuthenticationToken principal = (KeycloakAuthenticationToken) request.getUserPrincipal();
        String customerEmail = principal.getAccount().getKeycloakSecurityContext().getIdToken().getEmail();

        String mailSubject = "Twoje zam??wienie!";
        String mailText = "Dane odno??nie twojego zam??wienia poni??ej!";
        String bankAccountNumber = "Wp??a?? pieni??dze przelewem na: 06 1160 2202 0000 0001 9078 2527";
        String price = "Cena wynosi: " + shoppingCartService.getAmountTotal(getCustomerId()) + " z??";
        String boughtBooks = "Ksi????ki: " + sb.toString() + "\r\n";

        String mailText2 = "Dane do wysy??ki poni??ej";
        String name = loadShippingInfo().getFirstName() + " " + loadShippingInfo().getLastName();
        String address = loadShippingInfo().getAddress();
        String city = loadShippingInfo().getPostcode() + " " + loadShippingInfo().getCity();
        int phoneNumber = loadShippingInfo().getPhoneNumber();

        String result = "<h1>" + mailText + "</h1>" + "<h2>" + bankAccountNumber + "<h2>" + "<h2>" + price + "<h2>" +
                "<h2>" + boughtBooks + "<h2>" + "<h1>" + "________________________________________" + "<h1>" + "<h1>" +
                mailText2 + "</h1>" + "<h2>" + name + "<h2>" + "<h2>" + address + "<h2>" + "<h2>" + city + "<h2>" +
                "<h2>" + phoneNumber + "<h2>";

        mailService.sendMail("bookstoreappma@gmail.com", mailSubject, result, true);
        mailService.sendMail(customerEmail,mailSubject,result,true);

    }

}
