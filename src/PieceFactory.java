import java.util.HashMap;

public class PieceFactory {


    public Piece create(char shape, Color color) {
        Piece newPiece;

        switch (shape) {
            case 'Z':
                newPiece = new Piece();
                break;
            case 'O':
                newPiece = new Piece();
                break;
            case 'I':
                newPiece = new Piece();
                break;
            case 'S':
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
                break;
        }

        return newPiece;



        switch (gameName){
            case 'Tetris':
                newPiece = createTetris(color);
                break;

            case 'blokus':
                createBlokus(gameName, color)
                break;

            default:
                break;
        }

    }

    private createTetris(Color color) {
        Piece

    }

}
