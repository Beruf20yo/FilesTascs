package tasks.hamcrest;

import org.example.GameProgress;
import org.example.tasks.Task;
import org.example.tasks.TaskOne;
import org.example.tasks.TaskThree;
import org.example.tasks.TaskTwo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TaskThreeTest {
    @TempDir(cleanup = CleanupMode.ALWAYS)
    static Path tempDir;
    static String urlGames;
    static List<GameProgress> gameProgresses;
    @BeforeAll
    static void createTempDirUrl(){
        urlGames = tempDir.toFile().getPath();
        gameProgresses = new ArrayList<>(
                List.of(new GameProgress(94, 10, 15, 254.32),
                        new GameProgress(10, 1, 1, 16.0),
                        new GameProgress(15, 2, 6, 55.1)));
    }
    @Test
    void makeTaskThreeTest(){
        Task taskOne = new TaskOne(urlGames);
        taskOne.makeTask();
        Task taskTwo = new TaskTwo(urlGames);
        taskTwo.makeTask();
        TaskThree taskThree = new TaskThree(urlGames);
        List<GameProgress> listToCheck = taskThree.getProgressFromZip();
        for(int i = 0; i < listToCheck.size(); i ++){
            assertThat(gameProgresses.get(i), samePropertyValuesAs(listToCheck.get(i)));
        }
    }
    //New test
    @Test
    void checkGameProgress(){
        List<String> progressToString = gameProgresses.stream().map(GameProgress::toString).toList();
        for(int i = 0; i < progressToString.size(); i++){
            assertThat(gameProgresses.get(i),hasToString(progressToString.get(i)));
        }

    }
}
