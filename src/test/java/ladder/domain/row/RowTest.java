package ladder.domain.row;

import ladder.model.player.Position;
import ladder.model.row.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RowTest {
    @DisplayName("이전 값이 true면, 다음 값은 true가 오지 않는 Row 객체를 생성한다.")
    @Test
    void createTest() {
        //given
        int playerCount = 5;

        //when
        Row row = Row.create(playerCount);

        //then
        for (int i = 1; i < playerCount - 1; i++) {
            if (row.getRowElement(new Position(i))) {
                assertThat(row.getRowElement(new Position(i - 1))).isFalse();
            }
        }
    }
}
