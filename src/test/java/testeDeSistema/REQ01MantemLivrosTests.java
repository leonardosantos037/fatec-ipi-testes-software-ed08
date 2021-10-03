package testeDeSistema;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

class REQ01MantemLivrosTests {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeEach
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel-web.herokuapp.com/login");
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void ct01_cadastrar_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro não está cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		await();
		// *********************************************************************************
		// quando o usuário cadastrar um livro
		// *********************************************************************************
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).sendKeys("João Silva");
		driver.findElement(By.id("titulo")).sendKeys("O ano 2000");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		await();
		// *********************************************************************************
		// então o sistema apresenta as informações do livro
		// *********************************************************************************
		assertEquals(("Lista de livros"), driver.findElement(By.id("paginaConsulta")).getText());
		assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
		assertTrue(driver.getPageSource().contains("1234"));
		// *********************************************************************************
		// teardown - exclusão do registro
		// *********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	@Test
	public void ct11_consultar_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro está cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		await();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).sendKeys("João Silva");
		driver.findElement(By.id("titulo")).sendKeys("O ano 2000");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		await();
		// ******************************************************************************
		// quando o usuário consulta os livros cadastrados
		// ********************************************************************************
		driver.findElement(By.linkText("Voltar")).click();
		await();
		driver.findElement(By.linkText("Livros")).click();
		await();
		driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
		await();
		// *********************************************************************************
		// então o sistema apresenta as informações do livro
		// *********************************************************************************
		assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
		assertEquals(("Lista de livros"), driver.findElement(By.id("paginaConsulta")).getText());
		assertTrue(driver.getPageSource().contains("1234"));
		assertTrue(driver.getPageSource().contains("João Silva"));
		assertTrue(driver.getPageSource().contains("O ano 2000"));
		// *********************************************************************************
		// teardown - exclusao do registro
		// *********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	@Test
	public void ct13_atualizar_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro está cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		await();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).sendKeys("João Silva");
		driver.findElement(By.id("titulo")).sendKeys("O ano 2000");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		await();
		// *********************************************************************************
		// asserts com os objetivos de depuração
		// *********************************************************************************
		assertEquals("Lista de livros", driver.findElement(By.id("paginaConsulta")).getText());
		assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
		assertTrue(driver.getPageSource().contains("1234"));
		// *********************************************************************************
		// quando o usuário atualizar o livro
		// ********************************************************************************
		driver.findElement(By.linkText("Editar")).click();
		await();
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("João Pedro");
		driver.findElement(By.id("titulo")).sendKeys("Ano 2000");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		// *********************************************************************************
		// então o sistema apresenta as informações do livro atualizadas
		// *********************************************************************************
		assertEquals(("Lista de livros"), driver.findElement(By.id("paginaConsulta")).getText());
		assertTrue(driver.getPageSource().contains("1234"));
		assertTrue(driver.getPageSource().contains("João Pedro"));
		assertTrue(driver.getPageSource().contains("Ano 2000"));
		// *********************************************************************************
		// teardown - exclusão do registro
		// *********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
	}

	@Test
	public void ct19_excluir_livro_com_sucesso() {
		// *********************************************************************************
		// dado que o livro está cadastrado
		// *********************************************************************************
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		await();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1234");
		driver.findElement(By.id("autor")).sendKeys("João Silva");
		driver.findElement(By.id("titulo")).sendKeys("O ano 2000");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		await();
		// *********************************************************************************
		// asserts com os objetivos de depuração
		// *********************************************************************************
		assertEquals("Lista de livros", driver.findElement(By.id("paginaConsulta")).getText());
		assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());
		assertTrue(driver.getPageSource().contains("1234"));
		// ******************************************************************************
		// quando o usuário excluir o livro
		// ********************************************************************************
		driver.findElement(By.linkText("Excluir")).click();
		// *********************************************************************************
		// então sistema remove o livro
		// *********************************************************************************
		assertFalse(driver.getPageSource().contains("1234"));
		assertFalse(driver.getPageSource().contains("João Silva"));
		assertFalse(driver.getPageSource().contains("O ano 2000"));
	}

	public void await() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

}
