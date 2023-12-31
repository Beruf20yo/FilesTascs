package tasks.junit;

import org.example.GameProgress;
import org.example.tasks.Task;
import org.example.tasks.TaskOne;
import org.example.tasks.TaskThree;
import org.example.tasks.TaskTwo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.*;

public class TaskThreeTest {
    @TempDir(cleanup = CleanupMode.ALWAYS)
    static
    Path tempDir;
    static String urlGames;
    @BeforeAll
    static void createTempDirUrl(){
        urlGames = tempDir.toFile().getPath();
    }
    @Test
    void taskThree(){
        Task taskOne = new TaskOne(urlGames);
        taskOne.makeTask();
        Task taskTwo = new TaskTwo(urlGames);
        taskTwo.makeTask();
        TaskThree taskThree = new TaskThree(urlGames);
        List<GameProgress> gameProgresses = new ArrayList<>(
                List.of(new GameProgress(94, 10, 15, 254.32),
                        new GameProgress(10, 1, 1, 16.0),
                        new GameProgress(15, 2, 6, 55.1)));
        Set<GameProgress> setToCheck = new TreeSet<>(Comparator.comparingInt(GameProgress::getLvl));
        Set<GameProgress> setFromTask = new TreeSet<>(Comparator.comparingInt(GameProgress::getLvl));
        setToCheck.addAll(gameProgresses);
        setFromTask.addAll(taskThree.getProgressFromZip());
        Assertions.assertEquals(setToCheck,setFromTask);
    }
}