import javafx.scene.paint.Color;

public class PieceFactory {


    public Piece create(char shape, Color color) {
        boolean[][] tabCell = null;

        switch (shape) {
            /*case 'Z':
                newPiece = new Piece(color, );
                break;*/
            case 'O':
                tabCell = new boolean[2][2];
                tabCell[0][0] = true;
                tabCell[0][1] = true;
                tabCell[1][0] = true;
                tabCell[1][1] = true;
                break;
            case 'I':
                tabCell = new boolean[4][4];
                for(int i = 0; i < tabCell.length; i++)
                    for(int j = 0; j < tabCell[0].length; j++)
                        if(i == 1)
                            tabCell[i][j] = true;
                        else
                            tabCell[i][j] = false;
                break;
            /*case 'S':
                newPiece = new Piece();
                break;
            case 'L':
                newPiece = new Piece();
                break;
            case 'J':
                newPiece = new Piece();
                break;
            case 'T':
                newPiece = new Piece();
                break;*/
            default:
                tabCell = null;
        }

        return new Piece(color, new Position(0, 0), tabCell);
    }

}
