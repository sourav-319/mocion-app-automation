package org.mocion.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class EventDetailsPage extends BasePage {
    private static final String EVENT_DETAILS_SCREEN = "event_details_screen";

    public EventDetailsPage(AppiumDriver driver) {
        super(driver);
    }

    public EventDetailsPage clickCancelInscriptionButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "cancel_inscription_button");
        click(EVENT_DETAILS_SCREEN, "cancel_inscription_button");
        return this;
    }

    public void clickBookPlaceButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "book_place_button");
        click(EVENT_DETAILS_SCREEN, "book_place_button");
    }

    public void clickScheduleIcon() {
        click(EVENT_DETAILS_SCREEN, "schedule_icon");
    }

    public void clickRemoveMeFromWaitingListButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "remove_me_from_waiting_list_button");
        click(EVENT_DETAILS_SCREEN, "remove_me_from_waiting_list_button");
    }

    public void clickYestToConfirmCancelBooking() {
        click(EVENT_DETAILS_SCREEN, "yes_to_confirm_cancel_booking");
    }

    public void clickAddMeToWaitingListButton() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_button");
        click(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_button");
    }

    public void swipeToSeeAllPlayers(int swipeCount) {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "see_all_button");
        WebElement container = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().className(\"android.view.View\").clickable(true).descriptionContains(\"See all\")"
        ));

        int startX = container.getLocation().getX() + (int) (container.getSize().getWidth() * 0.9);
        int endX = container.getLocation().getX() + (int) (container.getSize().getWidth() * 0.1);
        int y = container.getLocation().getY() + container.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        for (int i = 0; i < swipeCount; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(swipe));
        }
    }

    public void verifyNoPartnerRepeatUntilAllMatched(Map<String, Map<String, List<List<String>>>> roundsData) {
        Map<String, Set<String>> playerPartnersMap = new HashMap<>();
        boolean passed = true;

        for (Map<String, List<List<String>>> courts : roundsData.values()) {
            for (List<List<String>> players : courts.values()) {
                List<String> partners = players.getFirst();
                for (String partner : partners) {
                    if (!playerPartnersMap.containsKey(partner)) {
                        playerPartnersMap.put(partner, new HashSet<>());
                    }
                    for (String other : partners) {
                        if (!partner.equals(other)) {
                            if (playerPartnersMap.get(partner).contains(other)) {
                                passed = false;
                                System.out.println("Partner repeated prematurely: " + partner + " with " + other);
                            }
                            playerPartnersMap.get(partner).add(other);
                        }
                    }
                }
            }
        }

        if (!passed) {
            throw new AssertionError("Player repeated a partner before matching with all other players.");
        }
    }

    public void verifyNoOpponentRepeat(Map<String, Map<String, List<List<String>>>> roundsData) {
        Map<String, Set<String>> playerOpponentsMap = new HashMap<>();
        boolean passed = true;

        for (Map<String, List<List<String>>> courts : roundsData.values()) {
            for (List<List<String>> players : courts.values()) {
                List<String> opponents = players.get(1);
                for (String opponent : opponents) {
                    if (!playerOpponentsMap.containsKey(opponent)) {
                        playerOpponentsMap.put(opponent, new HashSet<>());
                    }
                    for (String other : opponents) {
                        if (!opponent.equals(other)) {
                            if (playerOpponentsMap.get(opponent).contains(other)) {
                                passed = false;
                                System.out.println("Opponent repeated: " + opponent + " with " + other);
                            }
                            playerOpponentsMap.get(opponent).add(other);
                        }
                    }
                }
            }
        }

        if (!passed) {
            throw new AssertionError("Player repeated an opponent.");
        }
    }

    public void verifyOpponentRepeatWithLeastPlayed(Map<String, Map<String, List<List<String>>>> roundsData) {
        Map<String, Map<String, Integer>> playerOpponentCount = new HashMap<>();
        boolean passed = true;

        for (Map<String, List<List<String>>> courts : roundsData.values()) {
            for (List<List<String>> players : courts.values()) {
                List<String> opponents = players.get(1);
                for (String opponent : opponents) {
                    for (String otherOpponent : opponents) {
                        if (!opponent.equals(otherOpponent)) {
                            playerOpponentCount.putIfAbsent(opponent, new HashMap<>());
                            Map<String, Integer> counts = playerOpponentCount.get(opponent);

                            counts.put(otherOpponent, counts.getOrDefault(otherOpponent, 0) + 1);
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Map<String, Integer>> entry : playerOpponentCount.entrySet()) {
            String player = entry.getKey();
            Map<String, Integer> counts = entry.getValue();

            int minCount = counts.values().stream().min(Integer::compare).orElse(0);

            for (Map.Entry<String, Integer> countEntry : counts.entrySet()) {
                String opponent = countEntry.getKey();
                int count = countEntry.getValue();

                if (count > minCount) {
                    passed = false;
                    System.out.println("Player " + player + " repeated opponent " + opponent + " who has been played more times than others.");
                }
            }
        }

        if (!passed) {
            throw new AssertionError("Opponent repeats are not with the least played opponents.");
        }
    }

    public void verifyNoConsecutiveOpponents(Map<String, Map<String, List<List<String>>>> roundsData) {
        boolean passed = true;

        List<String> roundKeys = new ArrayList<>(roundsData.keySet());
        Collections.sort(roundKeys);

        for (int i = 0; i < roundKeys.size() - 1; i++) {
            String currentRoundKey = roundKeys.get(i);
            String nextRoundKey = roundKeys.get(i + 1);

            Map<String, List<List<String>>> currentRound = roundsData.get(currentRoundKey);
            Map<String, List<List<String>>> nextRound = roundsData.get(nextRoundKey);

            Set<String> currentMatches = new HashSet<>();
            for (List<List<String>> court : currentRound.values()) {
                List<String> team1 = court.get(0);
                List<String> team2 = court.get(1);
                for (String p1 : team1) {
                    for (String p2 : team2) {
                        currentMatches.add(p1 + "-" + p2);
                        currentMatches.add(p2 + "-" + p1);
                    }
                }
            }

            for (List<List<String>> court : nextRound.values()) {
                List<String> team1 = court.get(0);
                List<String> team2 = court.get(1);
                for (String p1 : team1) {
                    for (String p2 : team2) {
                        if (currentMatches.contains(p1 + "-" + p2)) {
                            System.out.println("Consecutive rounds opponent repeat: " + p1 + " vs " + p2 + " in rounds " + currentRoundKey + " and " + nextRoundKey);
                            passed = false;
                        }
                    }
                }
            }
        }

        if (!passed) {
            throw new AssertionError("Found consecutive rounds with same opponents.");
        }
    }

    public Map<String, Map<String, List<List<String>>>> getAllRoundsData(int totalRounds) {
        Map<String, Map<String, List<List<String>>>> roundsData = new LinkedHashMap<>();
        int[] courtInstances = {18, 19, 20};

        for (int round = 1; round <= totalRounds; round++) {
            Map<String, List<List<String>>> courtData = new LinkedHashMap<>();

            System.out.println("Round " + round);

            for (int instance : courtInstances) {
                WebElement block = driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().className(\"android.view.View\").instance(" + instance + ")"
                ));

                String contentDesc = block.getAttribute("contentDescription");
                if (contentDesc == null || contentDesc.isEmpty()) {
                    continue;
                }

                String[] lines = contentDesc.split("\\n");
                String courtName = lines.length > 0 ? lines[0] : "Unknown Court";

                List<String> partners = new ArrayList<>();
                List<String> opponents = new ArrayList<>();

                if (lines.length > 1) partners.add(lines[1]);
                if (lines.length > 2) partners.add(lines[2]);
                if (lines.length > 3) opponents.add(lines[3]);
                if (lines.length > 4) opponents.add(lines[4]);

                System.out.println("  Court: " + courtName);
                System.out.println("    Partners: " + partners);
                System.out.println("    Opponents: " + opponents);
                System.out.println();

                List<List<String>> players = new ArrayList<>();
                players.add(partners);
                players.add(opponents);

                courtData.put(courtName, players);
            }

            roundsData.put("Round " + round, courtData);

            if (round < totalRounds) {
                swipeRightToLeft(1);
            }
        }

        return roundsData;
    }

    public void swipeRightToLeft(int swipeCount) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        int startX = (int) (width * 0.9);
        int endX = (int) (width * 0.1);
        int y = height / 2;

        for (int i = 0; i < swipeCount; i++) {
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(swipe));
        }
    }

    public WebElement addMeToWaitingListSuccessLocator() {
        By locator = getLocator(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_success_message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement cancelInscriptionSuccessLocator() {
        By locator = getLocator(EVENT_DETAILS_SCREEN, "cancel_inscription_success_message");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement addMeToWaitingListButtonLocator() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_button");
        By locator = getLocator(EVENT_DETAILS_SCREEN, "add_me_to_waiting_list_button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement cancelInscriptionButtonLocator() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "cancel_inscription_button");
        By locator = getLocator(EVENT_DETAILS_SCREEN, "cancel_inscription_button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement removeMeFromWaitingListButtonLocator() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "remove_me_from_waiting_list_button");
        By locator = getLocator(EVENT_DETAILS_SCREEN, "remove_me_from_waiting_list_button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement bookPlaceButtonLocator() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "book_place_button");
        By locator = getLocator(EVENT_DETAILS_SCREEN, "book_place_button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement eventPlayerLocator() {
        scrollUntilVisible(EVENT_DETAILS_SCREEN, "event_player_locator");
        By locator = getLocator(EVENT_DETAILS_SCREEN, "event_player_locator");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isEventPlayerDisplayed() {
        By locator = null;
        try {
            locator = getLocator(EVENT_DETAILS_SCREEN, "event_player_locator");
        } catch (Exception ignored) {
        }

        if (locator != null) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                return elements.getFirst().isDisplayed();
            }
        }
        return false;
    }
}
