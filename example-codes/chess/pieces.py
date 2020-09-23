from .constants import PieceType
from .moves import ChessPosition
from .king import King
from .queen import Queen
from .knight import Knight
from .rook import Rook
from .bishop import Bishop
from .pawn import Pawn


class Piece:
    BLACK = "black"
    WHITE = "white"

    def __init__(self, position: ChessPosition, color):
        self._position = position
        self._color = color

    @property
    def position(self):
        return self._position

    @property
    def color(self):
        return self._color

    def move(self, target_position):
        self._position = target_position

    def get_threatened_positions(self, board):
        raise NotImplementedError

    def get_moveable_positions(self, board):
        raise NotImplementedError

    def symbol(self):
        black_color_prefix = '\u001b[31;1m'
        white_color_prefix = '\u001b[34;1m'
        color_suffix = '\u001b[0m'
        retval = self._symbol_impl()
        if self.color == Piece.BLACK:
            retval = black_color_prefix + retval + color_suffix
        else:
            retval = white_color_prefix + retval + color_suffix
        return retval

    def _symbol_impl(self):
        raise NotImplementedError

class PieceFactory:
    @staticmethod
    def create(piece_type: str, position: ChessPosition, color):
        if piece_type == PieceType.KING:
            return King(position, color)

        if piece_type == PieceType.QUEEN:
            return Queen(position, color)

        if piece_type == PieceType.KNIGHT:
            return Knight(position, color)

        if piece_type == PieceType.ROOK:
            return Rook(position, color)

        if piece_type == PieceType.BISHOP:
            return Bishop(position, color)

        if piece_type == PieceType.PAWN:
            return Pawn(position, color)
