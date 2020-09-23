from .render import ConsoleRender
from .game import ChessGame


class Player:
    def play_chess(self):
        render = ConsoleRender()
        game = ChessGame(render)
        game.run()


if __name__ == "__main__":
    player = Player
    player.play_chess()
