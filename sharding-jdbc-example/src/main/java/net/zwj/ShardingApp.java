package net.zwj;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShardingApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ShardingApp.class).bannerMode(Banner.Mode.OFF).build().run(args);
	}
	
    @Bean
    public CommandLineRunner runner(){
    	return arg -> {
    		System.out.println("hello sharding");
    	};
    }
}
