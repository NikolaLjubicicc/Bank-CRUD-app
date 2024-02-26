package rva;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestiranjeServera {
	@GetMapping("/zbir")
	public double zbirDvaBroja() {
		double prvi = Math.random()*10;
		double drugi = Math.random()*10;
		return prvi+drugi;
	}
}
