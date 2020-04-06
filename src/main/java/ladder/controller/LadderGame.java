package ladder.controller;

import ladder.model.player.PlayerName;
import ladder.model.player.Players;
import ladder.model.prize.LadderPrizes;
import ladder.model.result.GameResult;
import ladder.model.player.Position;
import ladder.model.result.PositionResult;
import ladder.model.row.Rows;
import ladder.view.InputView;
import ladder.view.ResultView;

import java.util.Map;

public class LadderGame {
    public static void start(InputView inputView) {
        Players players = Players.create(inputView.getPlayers());
        Rows rows = Rows.create(players, inputView.getHeight());
        LadderPrizes ladderPrizes = LadderPrizes.create(inputView.getLadderPrizes());
        ResultView resultView = new ResultView(players, rows);
        resultView.printLadder(ladderPrizes);

        PositionResult finalLocationByName
                = players.findFinalLocationByName(rows, inputView.getPlayerToGetResult());

        GameResult gameResult = GameResult.create(finalLocationByName, ladderPrizes);
        ResultView.printResult(gameResult);
    }
}