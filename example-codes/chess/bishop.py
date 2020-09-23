from .pieces import Piece


class Bishop(Piece):
    BEAM_INCREMENTS = [(1, 1), (1, -1), (-1, 1), (-1, -1)]

    def get_threatened_positions(self, board):
        positions = []
        for increment in Bishop.BEAM_INCREMENTS:
            positions += board.beam_search_threat(self._position, self._color, increment[0], increment[1])
        return positions

    def get_moveable_positions(self, board):
        return self.get_threatened_positions(board)

    def _symbol_impl(self):
        return 'BI'
