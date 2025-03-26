package com.vivek.EZeeLibrary;

import com.vivek.EZeeLibrary.menu.LibraryMenu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest(properties = {"spring.main.web-application-type=none", "spring.profiles.active=test"})
class EZeeLibraryApplicationTests {

	@MockitoBean
	private LibraryMenu libraryMenu;

	@Test
	void contextLoads() {
	}

}
