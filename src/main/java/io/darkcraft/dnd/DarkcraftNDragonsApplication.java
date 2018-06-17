package io.darkcraft.dnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching(proxyTargetClass=true)
public class DarkcraftNDragonsApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(DarkcraftNDragonsApplication.class, args);
	}
}
