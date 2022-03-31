package estore.service.mail;

import javax.mail.MessagingException;

import estore.repository.Account;
import estore.repository.Order;
import estore.repository.Share;

public interface MailerService {
	/**
	 * Gửi email
	 * @param mail thông tin email cần gửi
	 * @exception MessagingException lỗi gửi mail
	 */
	void send(Mail mail) throws MessagingException;
	/**
	 * Xếp mail vào hàng đợi để gửi theo lịch trình
	 * @param mail thông tin email được xếp vào hàng đợi
	 */
	void addToQueue(Mail mail);
	
	
	void sendShare(Share share);
	void sendOrder(Order order);
	void sendWelcome(Account account);
	void sendToken(String token, String email);
}