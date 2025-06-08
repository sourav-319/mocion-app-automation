package org.mocion.app.utils;

import io.appium.java_client.AppiumDriver;
import org.mocion.app.models.GestureCoordinates;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class GestureUtil {

    private static final PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    private GestureUtil() {
    }

    /**
     * Performs a tap at the center of the given element.
     *
     * @param driver  The Appium driver instance.
     * @param element The WebElement to tap on.
     */
    public static void tap(AppiumDriver driver, WebElement element) {
        Point location = element.getLocation();
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.x, location.y))
                .addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    /**
     * Performs a long press on an element.
     *
     * @param driver           The Appium driver instance.
     * @param element          The WebElement to long press on.
     * @param durationInMillis The duration of the long press in milliseconds.
     */
    public static void longPress(AppiumDriver driver, WebElement element, int durationInMillis) {
        // Get the center coordinates of the element
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int centerX = location.getX() + size.getWidth() / 2;
        int centerY = location.getY() + size.getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(durationInMillis)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    /**
     * Performs a swipe gesture in the specified direction.
     *
     * @param driver           The Appium driver instance.
     * @param direction        The direction to swipe. Allowed values: "left", "right", "up", "down".
     * @param durationInMillis The duration of the swipe in milliseconds.
     * @throws IllegalArgumentException If the direction provided is invalid.
     */
    public static void swipe(AppiumDriver driver, String direction, int durationInMillis) {
        Dimension size = driver.manage().window().getSize();
        int startX;
        int startY;
        int endX;
        int endY;

        switch (direction.toLowerCase()) {
            case "left":
                startX = (int) (size.width * 0.9);
                endX = (int) (size.width * 0.1);
                startY = endY = size.height / 2;
                break;
            case "right":
                startX = (int) (size.width * 0.1);
                endX = (int) (size.width * 0.9);
                startY = endY = size.height / 2;
                break;
            case "up":
                startY = (int) (size.height * 0.9);
                endY = (int) (size.height * 0.1);
                startX = endX = size.width / 2;
                break;
            case "down":
                startY = (int) (size.height * 0.1);
                endY = (int) (size.height * 0.9);
                startX = endX = size.width / 2;
                break;
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }

        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(FINGER.createPointerMove(Duration.ofMillis(durationInMillis), PointerInput.Origin.viewport(), endX, endY))
                .addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Performs a scroll in a given direction.
     *
     * @param driver           the AppiumDriver instance
     * @param direction        the direction to scroll (e.g., "up", "down", "left", "right")
     * @param durationInMillis the duration of the scroll in milliseconds
     */
    public static void scroll(AppiumDriver driver, String direction, int durationInMillis) {
        swipe(driver, direction, durationInMillis);
    }

    /**
     * Performs a pinch gesture to zoom out.
     *
     * @param driver  the AppiumDriver instance
     * @param element the WebElement to perform the pinch gesture on
     */
    public static void pinch(AppiumDriver driver, WebElement element) {
        Point center = element.getLocation();
        int startX1 = center.x - 100;
        int startY1 = center.y - 100;
        int startX2 = center.x + 100;
        int startY2 = center.y + 100;
        int endX1 = center.x;
        int endY1 = center.y;
        int endX2 = center.x;
        int endY2 = center.y;

        GestureCoordinates gestureCoordinates = GestureCoordinates.builder()
                .startX1(startX1)
                .startY1(startY1)
                .startX2(startX2)
                .startY2(startY2)
                .endX1(endX1)
                .endY1(endY1)
                .endX2(endX2)
                .endY2(endY2)
                .build();

        getSequence(driver, gestureCoordinates);
    }

    /**
     * Performs a pinch or zoom gesture using the given coordinates.
     *
     * @param driver      The Appium driver instance.
     * @param coordinates The GestureCoordinates object containing the start and end points for the gesture.
     */
    private static void getSequence(AppiumDriver driver, GestureCoordinates coordinates) {
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence pinch1 = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), coordinates.getStartX1(), coordinates.getStartY1()))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), coordinates.getEndX1(), coordinates.getEndY1()))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence pinch2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), coordinates.getStartX2(), coordinates.getStartY2()))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), coordinates.getEndX2(), coordinates.getEndY2()))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(pinch1));
        driver.perform(Collections.singletonList(pinch2));
    }

    /**
     * Performs a zoom gesture to zoom in.
     *
     * @param driver  the AppiumDriver instance
     * @param element the WebElement to zoom in on
     */
    public static void zoom(AppiumDriver driver, WebElement element) {
        Point center = element.getLocation();
        int startX1 = center.x;
        int startY1 = center.y;
        int startX2 = center.x;
        int startY2 = center.y;
        int endX1 = center.x - 100;
        int endY1 = center.y - 100;
        int endX2 = center.x + 100;
        int endY2 = center.y + 100;

        GestureCoordinates gestureCoordinates = GestureCoordinates.builder()
                .startX1(startX1)
                .startY1(startY1)
                .startX2(startX2)
                .startY2(startY2)
                .endX1(endX1)
                .endY1(endY1)
                .endX2(endX2)
                .endY2(endY2)
                .build();

        getSequence(driver, gestureCoordinates);
    }
}
