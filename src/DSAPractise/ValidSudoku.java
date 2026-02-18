package DSAPractise;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] subBox = new boolean[9][9];

        for(int i = 0;i<9;i++) {
            for(int j = 0;j<9;j++) {
                if(board[i][j] =='.') {
                    continue;
                }
                int num = board[i][j]-'1';
                int subBoxIndex = (i/3) *3 + (j/3);
                if(row[i][num]|| col[j][num] || subBox[subBoxIndex][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                subBox[subBoxIndex][num] = true;

            }
        }
        return true;
    }
}
