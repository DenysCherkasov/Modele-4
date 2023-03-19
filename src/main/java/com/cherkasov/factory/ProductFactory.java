package com.cherkasov.factory;

import com.cherkasov.model.Product;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ProductFactory {
    private int availableFuel;
    private int usedFuel;
    private int totalProducesFuel;
    private boolean continueProduceFuel = true;
    private int countConstructions;
    private int countProgrammingMicrocircuits;
    private int countBrokenMicrocircuits;
    private int countProductAssembly;
    private static final Object MONITOR = new Object();
    private static final Random RANDOM = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(Action.class);


    public Product createProduct() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        startRobots();
        stopWatch.stop();
        double stopWatchTime = stopWatch.getTime();
        double time = stopWatchTime/1000;
        return new Product(UUID.randomUUID().toString(), LocalDate.now(),
                totalProducesFuel, usedFuel, countBrokenMicrocircuits, time);
    }

    public void startRobots() throws InterruptedException {
        Thread firstRobot = new Thread(produceFuel);
        firstRobot.start();
        Thread secondRobot = new Thread(makeBasicConstruction);
        Thread thirdRobot = new Thread(makeBasicConstruction);
        secondRobot.start();
        thirdRobot.start();
        secondRobot.join();
        thirdRobot.join();
        Thread fourthRobot = new Thread(makeMicrocircuit);
        fourthRobot.start();
        fourthRobot.join();
        Thread fifthRobot = new Thread(makeProductAssembly);
        fifthRobot.start();
        fifthRobot.join();
    }

        private final Runnable produceFuel = () -> {
        LOGGER.info("First robot ({}) started to work", Thread.currentThread().getName());
        while (continueProduceFuel) {
            int producedFuel = RANDOM.nextInt(500, 1000);
            availableFuel += producedFuel;
            totalProducesFuel += producedFuel;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("First robot produced {} fuel, total produced fuel {}",
                    producedFuel, totalProducesFuel);
        }
        LOGGER.info("First robot ({}) has finished the work", Thread.currentThread().getName());
    };

    private final Runnable makeBasicConstruction = () -> {
        LOGGER.info("Second and third robot ({}) started to work",
                Thread.currentThread().getName());
        while (countConstructions <= 100) {
            int countToAdd = RANDOM.nextInt(10, 20);
            synchronized (MONITOR) {
                countConstructions += countToAdd;
            }
            LOGGER.info("Second and third robot ({}) make {} of work, total = {}",
                    Thread.currentThread().getName(), countToAdd, countConstructions);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Second and third robot ({} have finished the work", Thread.currentThread().getName());
    };

    private final Runnable makeMicrocircuit = () -> {
        LOGGER.info("Fourth robot ({}) have started to work", Thread.currentThread().getName());
        while (countProgrammingMicrocircuits <= 100) {
            int failureProbability = RANDOM.nextInt(100);
            if (failureProbability <= 30) {
                countProgrammingMicrocircuits = 0;
                countBrokenMicrocircuits += 1;
                LOGGER.info("Fourth robot ({}) have broken the microcircuit, " +
                        "total count of broken microcircuits: {}",
                        Thread.currentThread().getName(), countBrokenMicrocircuits);
                continue;
            }
            int countToAdd = RANDOM.nextInt(25, 35);
            countProgrammingMicrocircuits += countToAdd;
            LOGGER.info("Fourth robot ({}) make {} of work, total = {}",
                    Thread.currentThread().getName(), countToAdd,
                    countProgrammingMicrocircuits);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Fourth robot ({}) has finished the work",
                Thread.currentThread().getName());
    };

    private final Runnable makeProductAssembly = () -> {
        LOGGER.info("Fifth robot ({}) starts to work", Thread.currentThread().getName());
        while (countProductAssembly <= 100) {
            int countFuelNeed = RANDOM.nextInt(350, 700);
            if (availableFuel >= countFuelNeed) {
                usedFuel += countFuelNeed;
                availableFuel -= countFuelNeed;
                LOGGER.info("Fifth robot ({}) used {} fuel, total used fuel: {}",
                        Thread.currentThread().getName(), countFuelNeed, usedFuel);
                LOGGER.info("Available fuel {}", availableFuel);

                countProductAssembly += 10;
                LOGGER.info("Fifth robot ({}) make {} of work",
                        Thread.currentThread().getName(), countProductAssembly);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                LOGGER.info("There is no fuel for fifth robot ({})",
                        Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        continueProduceFuel = false;
        LOGGER.info("Fifth robot ({}) has finished the work",
                Thread.currentThread().getName());
    };
}
