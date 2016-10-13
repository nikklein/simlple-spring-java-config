package spring.java.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.java.domain.BlogPost;
import spring.java.domain.DataSource;
import spring.java.service.BlogPostService;
import spring.java.service.EmailService;

public class MainApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		//BlogPostService blogPostService = applicationContext.getBean(BlogPostService.class);
		
		BlogPostService blogPostService = (BlogPostService) applicationContext.getBean("blogPostService");
		
		BlogPost blogPost = new BlogPost();
		blogPost.setId(1L);
		blogPost.setTitle("JavaSpring MVC Java Config");
		
		blogPostService.savePost(blogPost);
		
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		
		LOGGER.debug("dataSource: username " + dataSource.getUsername());
		LOGGER.debug("dataSource: password " + dataSource.getPassword());
		
		EmailService emailService = applicationContext.getBean(EmailService.class);
		
		emailService.sendEmail();
		
		((ConfigurableApplicationContext) applicationContext).close();

	}

}
