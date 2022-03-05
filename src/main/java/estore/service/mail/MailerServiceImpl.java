package estore.service.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailerServiceImpl implements MailerService{
	@Autowired
	JavaMailSender sender;

	@Override
	public void send(Mail mail) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getText(), true);
		
		String from = mail.getFrom();
		if(from == null || from.trim().length() == 0) {
			from = "Online Shop <eshop@gmail.com>";
		}
		if(!from.contains("<")) {
			from = "%s <%s>".formatted(from, from);
		}
		helper.setFrom(from);
		helper.setReplyTo(from);
		
		String cc = mail.getCc();
		if(cc != null && cc.trim().length() > 0) {
			helper.setCc(cc);
		}
		
		String bcc = mail.getBcc();
		if(bcc != null && bcc.trim().length() > 0) {
			helper.setBcc(bcc);
		}
		
		String files = mail.getAttachments();
		if(files != null && files.trim().length() > 0) {
			Stream.of(files.split("[,;]+")).forEach(filename -> {
				try {
					File file = new File(filename);
					helper.addAttachment(file.getName(), file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		
		sender.send(msg);
	}

	List<Mail> queue = new ArrayList<>();
	
	@Override
	public void addToQueue(Mail mail) {
		queue.add(mail);
	}

	@Scheduled(fixedDelay = 2000)
	public void sendingScheduler() {
		while(!queue.isEmpty()) {
			Mail mail = queue.remove(0);
			try {
				this.send(mail);
				System.out.println("Success: " + mail.getTo());
			} catch (Exception e) {
				System.out.println("Error: " + mail.getTo());
				e.printStackTrace();
			}
		}
	}
}