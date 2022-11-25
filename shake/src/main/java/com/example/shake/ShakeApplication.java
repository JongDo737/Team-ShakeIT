package com.example.shake;

import com.example.shake.api.auto.Scheduler;
import com.example.shake.api.auto.URLConnect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@SpringBootApplication
public class ShakeApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShakeApplication.class, args);
		Scheduler scheduler = new Scheduler();
		System.out.println("아침 10시 : 00분 : 자동 메시지 기능이 설정되었습니다.");
		scheduler.execute(() -> {
			try {
				URLConnect.updateBill();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		},1,0,0);
		Scheduler scheduler2 = new Scheduler();
		System.out.println("밤 12시 00분 : 자동 업데이트 기능이 설정되었습니다.");
		scheduler2.execute(() -> {
			try {
				URLConnect.go();

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		},15,0,0); // 15 + 9 = 24
	}

}
