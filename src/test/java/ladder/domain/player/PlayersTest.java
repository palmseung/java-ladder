package ladder.domain.player;

import ladder.model.player.Player;
import ladder.model.player.Players;
import ladder.model.row.Row;
import ladder.model.row.Rows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayersTest {
    @DisplayName("Players 객체 생성")
    @Test
    void createTest() {
        //given
        List<Player> allPlayers = Arrays.asList(
                new Player("Mark"),
                new Player("Palm"),
                new Player("Jyung"));

        //when
        Players players = Players.create(allPlayers);

        //then
        assertThat(players.getPlayerCount()).isEqualTo(3);
    }

    @DisplayName("플레이어의 이름이 중복되면 예외를 던진다.")
    @Test
    void throwExceptionWhenDuplicationNamesTest(){
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Players players = Players.create(Arrays.asList(
                    new Player("Mark"),
                    new Player("Mark"),
                    new Player("Sujin")));
        });
    }

    @DisplayName("플레이어 이름을 입력하면, 최종 위치를 리턴한다.")
    @ParameterizedTest
    @CsvSource(value = {"Mark:2", "Ten:0", "Sujin:1"}, delimiter = ':')
    void findFinalLocationByNameTest(String input, int finalLocation) {
        //given
        Players players = Players.create(Arrays.asList(
                new Player("Mark", 0),
                new Player("Ten", 1),
                new Player("Sujin", 2)));

        Map<Integer, Boolean> result = new HashMap<>();
        result.put(0, true);
        result.put(1, false);
        Row row = new Row(result);

        Map<Integer, Boolean> result2 = new HashMap<>();
        result2.put(0, false);
        result2.put(1, true);
        Row row2 = new Row(result2);
        Rows rows = new Rows(Arrays.asList(row, row2));

        //when
        Map<String, Integer> finalLocationByName = players.findFinalLocationByName(rows, input);

        //then
        assertThat(finalLocationByName.get(input)).isEqualTo(finalLocation);
    }

    @DisplayName("존재하지 않는 플레이어명을 입력하면, 전체 결과를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"shusu", "Idle"})
    void findFinalLocationsByNameWhenNonValidName(String input) {
        //given
        Players players = Players.create(Arrays.asList(
                new Player("Mark", 0),
                new Player("Ten", 1),
                new Player("Sujin", 2)));

        Map<Integer, Boolean> result = new HashMap<>();
        result.put(0, true);
        result.put(1, false);
        Row row = new Row(result);

        Map<Integer, Boolean> result2 = new HashMap<>();
        result2.put(0, false);
        result2.put(1, true);
        Row row2 = new Row(result2);
        Rows rows = new Rows(Arrays.asList(row, row2));

        //when
        Map<String, Integer> finalLocationByName = players.findFinalLocationByName(rows, input);

        //then
        assertThat(finalLocationByName.size()).isEqualTo(3);
        assertThat(finalLocationByName.get("Mark")).isEqualTo(2);
        assertThat(finalLocationByName.get("Ten")).isEqualTo(0);
        assertThat(finalLocationByName.get("Sujin")).isEqualTo(1);
    }
}