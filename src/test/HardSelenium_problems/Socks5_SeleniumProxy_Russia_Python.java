import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class User {
    String authToken;
    String proxyIp;
    int proxyPort;
    String proxyLogin;
    String proxyPassword;
    String proxy;
    String message;
    String userAgent;
    String bearerToken;
    String guestToken;
    String xCsrfToken;
    CloseableHttpClient httpClient;
    List<String> chats;

    public User(String authToken, String proxyIp, int proxyPort, String proxyLogin, String proxyPassword, String message) {
        this.authToken = authToken;
        this.proxyIp = proxyIp;
        this.proxyPort = proxyPort;
        this.proxyLogin = proxyLogin;
        this.proxyPassword = proxyPassword;
        this.proxy = "socks5://" + proxyLogin + ":" + proxyPassword + "@" + proxyIp + ":" + proxyPort;
        this.message = message;
        // Placeholder for user agent
        this.userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
        this.httpClient = HttpClients.createDefault();
    }

    // Placeholder for data extraction
    public static User getData(String accountInfo) {
        try {
            String[] inf = accountInfo.split(":");
            if (inf.length != 6) {
                throw new IllegalArgumentException("Invalid number of elements in the string");
            }
            return new User(inf[0], inf[1], Integer.parseInt(inf[2]), inf[3], inf[4], inf[5]);
        } catch (Exception e) {
            System.err.println("Error processing account data: " + accountInfo + " - " + e.getMessage());
            return null;
        }
    }

    public static Map<String, String> waitForTokens(WebDriver driver, List<String> tokenNames) {
        Map<String, String> tokens = new HashMap<>();
        tokenNames.forEach(name -> tokens.put(name, null));
        long startTime = System.currentTimeMillis();
        while (tokens.values().contains(null) && (System.currentTimeMillis() - startTime < 10000)) {
            List<WebElement> logs = driver.findElements(By.tagName("performance"));
            for (WebElement log : logs) {
                // Parse JSON and extract tokens
                String message = log.getText();
                if (message.contains("Network.requestWillBeSent")) {
                    // Process the log to extract tokens
                    // Placeholder for actual token extraction
                }
            }
        }
        return tokens;
    }

    public static List<String> waitForUrls(WebDriver driver, String urlPrefix) {
        List<String> urls = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10000) {
            List<WebElement> logs = driver.findElements(By.tagName("performance"));
            for (WebElement log : logs) {
                // Parse JSON and extract URLs
                String message = log.getText();
                if (message.contains("Network.requestWillBeSent")) {
                    // Process the log to extract URLs
                    // Placeholder for actual URL extraction
                }
            }
        }
        return urls;
    }

    public static void continuousScroll(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='cellInnerDiv']/div[@data-testid='activeRoute']")));

        int count = 0;
        String lastName = "";
        while (true) {
            String newName = lastName;
            List<WebElement> messageElements = driver.findElements(By.xpath("//div[@data-testid='cellInnerDiv']/div[@data-testid='activeRoute']"));
            if (!messageElements.isEmpty()) {
                WebElement lastElement = messageElements.get(messageElements.size() - 1);
                lastElement.sendKeys(""); // Scroll to last element
            } else {
                System.out.println("No elements to scroll.");
            }
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            lastName = messageElements.isEmpty() ? "" : messageElements.get(messageElements.size() - 1).getText();
            if (newName.equals(lastName)) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) {
                break;
            }
        }
    }

    public void processUser() {
        Logger logger = Logger.getLogger(User.class.getName());
        logger.setLevel(Level.INFO);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--log-level=3");
        options.addArguments("--silent");
        options.addArguments("--user-agent=" + this.userAgent);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://x.com/");
            Map<String, String> tokens = waitForTokens(driver, Arrays.asList("authorization", "x-guest-token"));

            String bearerToken = tokens.get("authorization") != null ? tokens.get("authorization").split(" ")[1] : null;
            String guestToken = tokens.get("x-guest-token");

            if (bearerToken != null) {
                logger.info("Bearer: " + bearerToken);
                this.bearerToken = bearerToken;
            } else {
                logger.warning("Bearer token not found");
            }

            if (guestToken != null) {
                logger.info("Guest Token: " + guestToken);
                this.guestToken = guestToken;
            } else {
                logger.warning("Guest Token not found");
            }

            driver.manage().addCookie(new org.openqa.selenium.Cookie("auth_token", this.authToken));

            driver.get("https://x.com/messages");
            tokens = waitForTokens(driver, Collections.singletonList("x-csrf-token"));
            continuousScroll(driver);
            String xCsrfToken = tokens.get("x-csrf-token");

            if (xCsrfToken != null) {
                logger.info("x-csrf-token: " + xCsrfToken);
                this.xCsrfToken = xCsrfToken;
                System.out.println("All required tokens obtained");

                // Process URLs
                String urlPrefix = "https://x.com/i/api/1.1/dm/inbox_timeline/trusted.json";
                List<String> trustedUrls = waitForUrls(driver, urlPrefix);
                trustedUrls.add("https://x.com/i/api/1.1/dm/user_updates.json?..."); // Add the full URL here

                driver.quit();

                CloseableHttpClient httpClient = HttpClients.createDefault();
                String finalBearerToken = bearerToken;
                String finalGuestToken = guestToken;
                String finalXCsrfToken = xCsrfToken;
                this.httpClient = httpClient;

                Set<String> conversations = new HashSet<>();

                for (String url : trustedUrls) {
                    HttpGet request = new HttpGet(url);
                    request.setHeader("Authorization", "Bearer " + finalBearerToken);
                    request.setHeader("Cookie", "auth_token=" + this.authToken + "; ct0=" + finalXCsrfToken);
                    request.setHeader("x-csrf-token", finalXCsrfToken);
                    request.setHeader("x-guest-token", finalGuestToken);
                    request.setHeader("User-Agent", this.userAgent);

                    try (CloseableHttpResponse response = httpClient.execute(request)) {
                        if (response.getStatusLine().getStatusCode() == 200) {
                            String json = EntityUtils.toString(response.getEntity());
                            // Extract conversation IDs from JSON
                            // Placeholder for JSON parsing
                        } else {
                            logger.severe("Request error: " + response.getStatusLine().getStatusCode());
                        }
                    }
                }

                // Generate links from conversation IDs
                List<String> links = conversations.stream()
                        .map(id -> "https://x.com/messages/" + id.strip())
                        .collect(Collectors.toList());

                System.out.println("Total groups: " + links.size());
                System.out.println("Groups: " + links);
                this.chats = links;
            }

        } catch (Exception e) {
            System.err.println("Error processing user: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

}


/*
import logging
from seleniumwire import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.by import By
import json
import aiohttp
import sys
from fake_useragent import UserAgent
import time
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import requests

class User:
        def __init__(self, auth_token, proxy_ip, proxy_port, proxy_login, proxy_password, message):
        self.auth_token = auth_token
        self.proxy_ip = proxy_ip
        self.proxy_port = proxy_port
        self.proxy_login = proxy_login
        self.proxy_password = proxy_password
        self.proxy = f"socks5://{proxy_login}:{proxy_password}@{proxy_ip}:{proxy_port}"
        self.message = message
        self.useragent = UserAgent().random
        self.bearer_token = None
        self.guest_token = None
        self.x_csrf_token = None
        self.session = None
        self.chats = None





        def get_data(account_info):
        try:
        inf = account_info.split(":")
        if len(inf) != 6:
        raise ValueError("Неверное количество элементов в строке")
        return User(auth_token=inf[0], proxy_ip=inf[1], proxy_port=inf[2], proxy_login=inf[3], proxy_password=inf[4], message=inf[5])
        except Exception as e:
        print(f"Ошибка обработки данных аккаунта: {account_info} - {str(e)}")
        return None

        def wait_for_tokens(driver, token_names):
        tokens = {name: None for name in token_names}
        while None in tokens.values():
        logs = driver.get_log('performance')
        for log in logs:
        log_json = json.loads(log['message'])
        message = log_json['message']
        if message['method'] == 'Network.requestWillBeSent':
        headers = message.get('params', {}).get('request', {}).get('headers', {})
        for name in token_names:
        if name in headers:
        tokens[name] = headers[name]
        if all(tokens.values()):
        return tokens

        def wait_for_urls(driver, url_prefix):
        urls = []
        start_time = time.time()
        while time.time() - start_time < 10:
        logs = driver.get_log('performance')
        for log in logs:
        log_json = json.loads(log['message'])
        message = log_json['message']
        if message['method'] == 'Network.requestWillBeSent':
        url = message.get('params', {}).get('request', {}).get('url', '')
        if url.startswith(url_prefix):
        urls.append(url)
        print(f"Найден URL: {url}")
        print()
        return urls

        def continuous_scroll(driver):
        WebDriverWait(driver, 60).until(
        EC.presence_of_element_located((By.XPATH, "//div[@data-testid='cellInnerDiv']/div[@data-testid='activeRoute']"))
        )

        count = 0
        last_name = ""
        act = ActionChains(driver)

        while True:
        new_name = last_name
        # Обновление списка элементов сообщений
        message_elements = driver.find_elements(By.XPATH, "//div[@data-testid='cellInnerDiv']/div[@data-testid='activeRoute']")
        message_element_count = len(message_elements)

        # Прокрутка к последнему элементу
        if message_element_count > 0:
        last_element = message_elements[-1]
        act.move_to_element(last_element).perform()
        else:
        print("Нет элементов для прокрутки.")

        time.sleep(7)  # Задержка между прокрутками

        last_name = last_element.text
        if new_name == last_name:
        count += 1
        else:
        count = 0

        if count == 3:
        break






        def process_user(account):
        # Настройки логгирования
        logging.basicConfig(level=logging.INFO, stream=sys.stdout, format='%(asctime)s - %(levelname)s - %(message)s')
        # Отключить подробные логи selenium-wire и urllib3
        logging.getLogger('seleniumwire').setLevel(logging.WARNING)
        logging.getLogger('urllib3').setLevel(logging.WARNING)

        logging.info(f"Используем прокси: {account.proxy}")
        proxies = {
        'http': account.proxy,
        'https': account.proxy
        }

        # Настройки браузера
        chrome_options = Options()
        chrome_options.add_argument("--disable-blink-features=AutomationControlled")
        # Запускаем браузер в фоновом режиме
        chrome_options.add_argument("--no-sandbox")
        chrome_options.add_argument("--disable-dev-shm-usage")
        chrome_options.page_load_strategy = 'none'  # Загружаем страницу до появления видимого контента

        chrome_options.add_argument("--log-level=3")
        chrome_options.add_argument("--silent")

        proxy_url = f"http://{account.proxy_login}:{account.proxy_password}@{account.proxy_ip}:{account.proxy_port}"

        seleniumwire_options = {
        "proxy": {
        "http": account.proxy,
        "https": account.proxy,
        }
        }

        # Установка Desired Capabilities для логирования сетевых запросов
        chrome_options.set_capability('goog:loggingPrefs', {'performance': 'ALL'})

        # Добавление пользовательского агента
        user_agent = account.useragent
        chrome_options.add_argument(f"user-agent={user_agent}")

        # Создаем объект драйвера
        driver = webdriver.Chrome(seleniumwire_options=seleniumwire_options, options=chrome_options)
        try:
        driver.get("https://x.com/")
        tokens = wait_for_tokens(driver, ['authorization', 'x-guest-token'])

        bearer_token = tokens['authorization'].split(' ')[1] if tokens['authorization'] else None
        guest_token = tokens['x-guest-token']

        if bearer_token:
        logging.info(f"Bearer: {bearer_token}")
        account.bearer_token = bearer_token
        else:
        logging.warning("Не удалось найти Bearer токен")

        if guest_token:
        logging.info(f"Guest Token: {guest_token}")
        account.guest_token = guest_token
        else:
        logging.warning("Не удалось найти Guest Token")

        driver.add_cookie({'name': 'auth_token', 'value': account.auth_token})

        # Переход на страницу сообщений для получения x-csrf-token
        driver.get("https://x.com/messages")
        tokens = wait_for_tokens(driver, ['x-csrf-token'])
        continuous_scroll(driver=driver)
        x_csrf_token = tokens['x-csrf-token']

        if x_csrf_token:
        logging.info(f"x-csrf-token: {x_csrf_token}")
        account.x_csrf_token = x_csrf_token
        print("Все необходимые токены получены")
        #Пошел процесс получения чатов чатов
        url_prefix = "https://x.com/i/api/1.1/dm/inbox_timeline/trusted.json"
        trusted_urls = wait_for_urls(driver, url_prefix)
        trusted_urls.append("https://x.com/i/api/1.1/dm/user_updates.json?nsfw_filtering_enabled=false&dm_secret_conversations_enabled=false&krs_registration_enabled=true&cards_platform=Web-12&include_cards=1&include_ext_alt_text=true&include_ext_limited_action_results=true&include_quote_count=true&include_reply_count=1&tweet_mode=extended&include_ext_views=true&dm_users=false&include_groups=true&include_inbox_timelines=true&include_ext_media_color=true&supports_reactions=true&include_ext_edit_control=true&include_ext_business_affiliations_label=true&ext=mediaColor%2CaltText%2CbusinessAffiliationsLabel%2CmediaStats%2ChighlightedLabel%2CvoiceInfo%2CbirdwatchPivot%2CsuperFollowMetadata%2CunmentionInfo%2CeditControl%2Carticle")
        driver.quit()
        headers = {
        'authorization': f'Bearer {bearer_token}',
        'cookie': f'auth_token={account.auth_token}; ct0={x_csrf_token}',
        'x-csrf-token': x_csrf_token,
        'x-guest-token': guest_token,
        'user-agent': user_agent
        }
        account.session = requests.Session()
        conversations = set()

        def extract_conversation_ids1(json_data):
        conversation_ids = set()

        if 'inbox_initial_state' in json_data:
        conversations = json_data['inbox_initial_state'].get('conversations', {})
        for conv_id, conv_data in conversations.items():
        if 'conversation_id' in conv_data:
        conversation_ids.add(conv_data['conversation_id'])
        elif 'inbox_timeline' in json_data:
        conversations = json_data['inbox_timeline'].get('conversations', {})
        for conv_id, conv_data in conversations.items():
        if 'conversation_id' in conv_data:
        conversation_ids.add(conv_data['conversation_id'])

        return conversation_ids

        # Используем полученные токены в запросе
        for i in range(len(trusted_urls)):
        response = account.session.get(url=trusted_urls[i], headers=headers, proxies=proxies)
        if response.status_code == 200:
        data = response.json()
        conversation_ids = extract_conversation_ids1(data)

        # Сохранение conversation_ids в множество
        for conversation_id in conversation_ids:
        conversations.add(conversation_id)

        else:
        logging.error(f"Ошибка при выполнении запроса: {response.status_code}")
        logging.error(response.text)

        def linkmaker():
        links = []
        for i in conversations:
        link = "https://x.com/messages/" + i.strip()
        links.append(link)
        return links

        links = linkmaker()
        print(f"Общее число групп: {len(links)}")
        print("Группы:", links)
        account.chats = links
        except ValueError:
        print("Ошибка где- то")

        async def sending(user):
        aiohttp.get
        pass

        def main():
        while True:
        Taccount = int(input("Введите время, которое будет работать суммарно программа (в минутах): "))
        Accounts = input("Введите аккаунты (формат ввода: 'authtoken:proxy_ip:proxy_port:proxy_login:proxy_password:сообщение;authtoken:proxy_ip:proxy_port:proxy_login:proxy_password:сообщение'): ").split(";")

        accounts_list = [get_data(account_info) for account_info in Accounts]

        # Фильтруем None из списка аккаунтов
        accounts_list = [account for account in accounts_list if account is not None]

        # Проверим результат
        print("СЕЙЧАС ВАМ БУДУТ ВЫВЕДЕНЫ ВАШИ ДАННЫЕ, ПОЖАЛУЙСТА ПРОВЕРЬТЕ ПРАВИЛЬНОСТЬ ИХ ЗАПОЛНЕНИЯ")
        time.sleep(4)
        for account in accounts_list:
        print(f"Auth Token: {account.auth_token}, Proxy: {account.proxy}, Message: {account.message}")

        check = input("Если данные заполнены правильно, нажмите + если нет - ")

        if check == "+":
        break
        elif check == "-":
        print("Пожалуйста, введите данные заново.")
        else:
        print("Неверный ввод. Пожалуйста, выберите + или -.")

        print("ОТЛИЧНО, ЗАПУЩЕН ПРОЦЕСС ОБРАБОТКИ ДАННЫХ, ПОЖАЛУЙСТА ПОДОЖДИТЕ...")
        print()
        for account in accounts_list:
        process_user(account)

        if __name__ == "__main__":
        main()



*/




