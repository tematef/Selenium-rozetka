package com.rozetka.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.*;

import static com.rozetka.selenium.utils.ProfileSetup.getGeckoDriverPath;
import static com.rozetka.selenium.utils.ProfileSetup.setFirefoxProfile;

/**
 * Created by artem on 3/24/17.
 */
public abstract class BasicTestCase {

    protected WebDriver driver;
//    private static final Logger LOG = LoggerFactory.getLogger(BasicTestCase.class);
    private String testName = "beforeClass";
    private String screenDir;

    @BeforeClass
    public void beforeClass() {
//        driver = (WebDriver) Proxy.newProxyInstance(
//                getClass().getClassLoader(),
//                new Class<?>[]{WebDriver.class, JavascriptExecutor.class},
//        LOG.debug("Driver selection is opened");
        System.setProperty("webdriver.firefox.marionette", getGeckoDriverPath());
        driver = new FirefoxDriver();
//        driver = new FirefoxDriver(setFirefoxProfile());
//        switch (browser) {
//            case "*chrome":
//                LOG.debug("Chrome is selected");
//                String chromePath = getChromeDriverPath();
//                LOG.debug("Chrome path:" + chromePath );
//                System.setProperty("webdriver.chrome.driver",chromePath );
//                realDriver = new ChromeDriver(createChromeConfig());
//                LOG.debug("Chrome driver is created");
//                break;
//            case "*mobile.chrome":
//                System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
//                realDriver = new ChromeDriver(createMobileChromeConfig());
//                break;
//            case "*firefox":
//            default:
//                System.setProperty("webdriver.firefox.marionette", getGeckoDriverPath());
//                realDriver = new FirefoxDriver(setFirefoxProfile());
//        }
//        new File(screenDir).mkdir();
    }

//    @BeforeMethod
//    public void handleTestMethodName(Method method) {
//        testName = method.getName();
//    }

    @AfterClass(alwaysRun = true)
    public synchronized void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }


    protected void assertEquals(Object actualObject, Object expectedObject) {
        try {
            Assert.assertEquals(actualObject, expectedObject);
        } catch (AssertionError e) {
            throw new AssertionError("\nExpected: " + expectedObject.toString() + "\nActual:" + actualObject.toString()
                    + "\nMessage: " + e);
        }
    }



//    private class WebDriverInvocationHandler implements InvocationHandler {
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            WebDriverMethod smethod = WebDriverMethod.unknown;
//            try {
//                smethod = WebDriverMethod.valueOf(method.getName());
//            } catch (IllegalArgumentException e) {
//            }
//            boolean captureElements = false;
//            boolean captureElement = false;
//            // debug output
//            switch (smethod) {
//                case get:
//                    LOG.debug("opening url: " + args[0]);
//                    break;
//                case findElements:
//                    captureElements = true;
//                    LOG.debug("finding elements: " + args[0]);
//                    break;
//                case findElement:
//                    captureElement = true;
//                    LOG.debug("finding element: " + args[0]);
//                    break;
//                case close:
//                    LOG.debug("closing window (quitting browser if only window)");
//                    break;
//                case quit:
//                    LOG.debug("closing web browser");
//                    break;
//                default:
//                    LOG.debug("invoking " + method.getName());
//                    break;
//            }
//
//            // invoke the method
//            Object ret;
//            try {
//                ret = method.invoke(realDriver, args);
//                if (captureElement) {
//                    WebElement webElementProxy = getNewWebElementProxy(ret);
//                    ret = webElementProxy;
//                } else if (captureElements) {
//                    @SuppressWarnings("unchecked")
//                    List<WebElement> retList = ((List<WebElement>) ret);
//                    for (int i = 0; i < retList.size(); i = i + 1) {
//                        WebElement webElementProxy = getNewWebElementProxy(retList.get(i));
//                        retList.set(i, webElementProxy);
//                    }
//                    ret = retList;
//                }
//            } catch (InvocationTargetException e) {
//                throw e.getCause();
//            }
//
//            return ret;
//        }
//
//        private WebElement getNewWebElementProxy(final Object ret) {
//            return (WebElement) Proxy.newProxyInstance(
//                    getClass().getClassLoader(),
//                    new Class<?>[]{WebElement.class},
//                    new InvocationHandler() {
//                        Object realWebElement = ret;
//
//                        @Override
//                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                            logWebElementAction(ret, method, args);
//                            // invoke the method
//                            Object ret;
//                            try {
//                                ret = method.invoke(realWebElement, args);
//                            } catch (InvocationTargetException e) {
//                                if (e.getCause().getClass() == StaleElementReferenceException.class) {
//                                    Thread.sleep(500);
//                                    return reinvoke(proxy, e.getCause(), method, args);
//                                }
//                                throw e.getCause();
//                            }
//                            return ret;
//                        }
//
//                        private Object reinvoke(Object proxy, Throwable cause, Method method, Object[] args) throws Throwable {
//                            LOG.debug("Re-running command. Caught: " + cause.getClass());
//                            Object ret;
//                            try {
//                                ret = method.invoke(realWebElement, args);
//                            } catch (InvocationTargetException e) {
//                                throw e.getCause();
//                            }
//
//                            return ret;
//                        }
//                    });
//        }
//
//        void logWebElementAction(Object ret, Method method, Object[] args) {
//            WebElementMethod smethod = WebElementMethod.unknown;
//            try {
//                smethod = WebElementMethod.valueOf(method.getName());
//            } catch (IllegalArgumentException e) {
//            }
//
//            // debug output
//            switch (smethod) {
//                case clear:
//                    LOG.debug("clearing text: " + ret);
//                    break;
//                case click:
//                    LOG.debug("clicking: " + ret);
//                    break;
//                case findElement:
//                    LOG.debug("finding element: " + args[0]);
//                    break;
//                case findElements:
//                    LOG.debug("finding elements: " + args[0]);
//                    break;
//                case sendKeys:
//                    LOG.debug("sending: \"" + concat((CharSequence[]) args[0]) + "\"");
//                    break;
//                case getAttribute:
//                    LOG.debug("getting attribute: " + args[0]);
//                    break;
//                case submit:
//                    LOG.debug("submiting" + args[0]);
//                    break;
//                default:
//                    LOG.debug("invoking " + method.getName());
//                    break;
//            }
//        }
//
//        private String concat(CharSequence[] charSequences) {
//            StringBuilder buf = new StringBuilder();
//            for (CharSequence seq : charSequences) {
//                buf.append(seq);
//            }
//            return buf.toString();
//        }
//    }

    private enum WebDriverMethod {
        get,
        findElements,
        findElement,
        close,
        quit,
        unknown
    }

    private enum WebElementMethod {
        clear,
        click,
        findElement,
        findElements,
        sendKeys,
        submit,
        getAttribute,
        unknown
    }
}
