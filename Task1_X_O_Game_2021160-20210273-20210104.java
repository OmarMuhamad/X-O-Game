import java.util.Scanner;

class Test{
    public static void main(String[] arg){
        Board board =new Board();
        Player[] players = {new Player("player1", 'X'), new Player("player2", 'O')};
        Game game = new Game(board, players);
        game.play_game();
    }

}
class Game{
    private final Board board;
    Player[] players;
    private int turn = 0;
    public Game(Board _board, Player[] players){
        this.board = _board;
        this.players = players;
    }
    public void play_game(){
        Player p1 = players[0];
        Player p2 = players[1];
        while(true){
            board.display_board();
            if(turn == 0){
                System.out.println(p1.get_name() + " Enter your move (x, y) coordinates: ");
                p1.get_move(new Scanner(System.in).nextInt(), new Scanner(System.in).nextInt());
                // if player[0] wins, then end game
                if(!board.update_board(p1.get_x(), p1.get_y(), p1.get_symbol()))
                    play_game();
                if(board.is_winner(p1.get_symbol())){
                    System.out.println(p1.get_name() + " Wins.o_o");
                    break;
                }
                else if(board.is_draw()){
                    System.out.println("There a Tie :(");
                    break;
                }
            }else{
                System.out.println(p2.get_name() + " Enter your move (x, y) coordinates: ");
                p2.get_move(new Scanner(System.in).nextInt(), new Scanner(System.in).nextInt());
                if(!board.update_board(p2.get_x(), p2.get_y(), p2.get_symbol())){
                    play_game();
                }
                // if player[1] wins, then end game
                if(board.is_winner(p2.get_symbol())){
                    System.out.println(p2.get_name() + " Wins.o_o");
                    break;
                }
                else if(board.is_draw()){
                    System.out.println("There a Tie :(");
                    break;
                }
            }
            if(turn == 0)
                turn = 1;
            else
                turn = 0;
        }
    }
}

class Board{
    char[][] board = new char[3][3];
    int moves = 0;
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
    }
    public boolean update_board(int x, int y, char symbol) {
        if (!(x > 2 || x < 0 || y > 2 || y < 0) && board[x][y] == '_') {
            board[x][y] = symbol;
            moves++;
            return true;
        }
        System.out.println("Invalid Move !");
        return false;
    }
    public void display_board() {
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j] + " | ");

            }
            System.out.println();

        }
        System.out.println("-------------");
    }
    public boolean is_winner(char symbol) {
        if (board[0][0] != '_' && board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][2]==symbol) return true;
        if (board[1][0] != '_' && board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][2]==symbol) return true;
        if (board[2][0] != '_' && board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][2]==symbol) return true;
        if (board[0][0] != '_' && board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[2][0]==symbol) return true;
        if (board[0][1] != '_' && board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[2][1]==symbol) return true;
        if (board[0][2] != '_' && board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[2][2]==symbol) return true;
        if (board[0][0] != '_' && board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[2][2]==symbol) return true;
        if (board[0][2] != '_' && board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[2][0]==symbol) return true;
        return false;
    }
    public boolean is_draw() {
        return (moves == 9);
    }
}
class Player{
    private final String name;
    private final char symbol;
    private int x, y;
    public Player(String _name, char _symbol){
        this.name = _name;
        this.symbol = _symbol;
    }
    public void get_move(int x, int y){
        this.x = x;
        this.y = y;
    }
    public char get_symbol(){
       return this.symbol;
    }
    public String get_name(){
        return this.name;
    }
    public int get_x(){
        return this.x;
    }
    public int get_y(){
        return this.y;
    }
}