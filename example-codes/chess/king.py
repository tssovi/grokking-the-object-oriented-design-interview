from .pieces import Piece
from .moves import ChessPosition


class King(Piece):
    SPOT_INCREMENTS = [(1, -1), (1, 0), (1, 1), (0, 1), (-1, 1), (-1, 0), (-1, -1), (0, -1)]

    def __init__(self, position: ChessPosition, color: str):
        super().__init__(position, color)
        self._board_handle = None

    def set_board_handle(self, board):
        self._board_handle = board
        self._board_handle.register_king_position(self.position, self.color)

    def move(self, target_position: ChessPosition):
        Piece.move(self, target_position)
        self._board_handle.register_king_position(target_position, self.color)

    def get_threatened_positions(self, board):
        positions = []
        for increment in King.SPOT_INCREMENTS:
            positions.append(board.spot_search_threat(self._position, self._color, increment[0], increment[1]))
        positions = [x for x in positions if x is not None]
        return positions

    def get_moveable_positions(self, board):
        return self.get_threatened_positions(board)

    def _symbol_impl(self):
        return 'KI'
