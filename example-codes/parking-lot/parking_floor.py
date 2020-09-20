from .constants import  *
from .parking_display_board import *


class ParkingFloor:
    def __init__(self, name):
        self.__name = name
        self.__handicapped_spots = {}
        self.__compact_spots = {}
        self.__large_spots = {}
        self.__motorbike_spots = {}
        self.__electric_spots = {}
        self.__info_portals = {}
        self.__free_handicapped_spot_count = {'free_spot': 0}
        self.__free_compact_spot_count = {'free_spot': 0}
        self.__free_large_spot_count = {'free_spot': 0}
        self.__free_motorbike_spot_count = {'free_spot': 0}
        self.__free_electric_spot_count = {'free_spot': 0}
        self.__display_board = ParkingDisplayBoard()

    def add_parking_spot(self, spot):
        switcher = {
            ParkingSpotType.HANDICAPPED: self.__handicapped_spots.put(spot.get_number(), spot),
            ParkingSpotType.COMPACT: self.__compact_spots.put(spot.get_number(), spot),
            ParkingSpotType.LARGE: self.__large_spots.put(spot.get_number(), spot),
            ParkingSpotType.MOTORBIKE: self.__motorbike_spots.put(spot.get_number(), spot),
            ParkingSpotType.ELECTRIC: self.__electric_spots.put(spot.get_number(), spot),
        }
        switcher.get(spot.get_type(), 'Wrong parking spot type')

    def assign_vehicleToSpot(self, vehicle, spot):
        spot.assign_vehicle(vehicle)
        switcher = {
            ParkingSpotType.HANDICAPPED: self.update_display_board_for_handicapped(spot),
            ParkingSpotType.COMPACT: self.update_display_board_for_compact(spot),
            ParkingSpotType.LARGE: self.update_display_board_for_large(spot),
            ParkingSpotType.MOTORBIKE: self.update_display_board_for_motorbike(spot),
            ParkingSpotType.ELECTRIC: self.update_display_board_for_electric(spot),
        }
        switcher(spot.get_type(), 'Wrong parking spot type!')

    def update_display_board_for_handicapped(self, spot):
        if self.__display_board.get_handicapped_free_spot().get_number() == spot.get_number():
            # find another free handicapped parking and assign to display_board
            for key in self.__handicapped_spots:
                if self.__handicapped_spots.get(key).is_free():
                    self.__display_board.set_handicapped_free_spot(self.__handicapped_spots.get(key))

            self.__display_board.show_empty_spot_number()

    def update_display_board_for_compact(self, spot):
        if self.__display_board.get_compact_free_spot().get_number() == spot.get_number():
            # find another free compact parking and assign to display_board
            for key in self.__compact_spots.key_set():
                if self.__compact_spots.get(key).is_free():
                    self.__display_board.set_compact_free_spot(self.__compact_spots.get(key))

            self.__display_board.show_empty_spot_number()

    def free_spot(self, spot):
        spot.remove_vehicle()
        switcher = {
            ParkingSpotType.HANDICAPPED: self.__free_handicapped_spot_count.update(
              free_spot = self.__free_handicapped_spot_count["free_spot"] + 1
            ),
            ParkingSpotType.COMPACT: self.__free_compact_spot_count.update(
              free_spot=self.__free_compact_spot_count["free_spot"] + 1
            ),
            ParkingSpotType.LARGE: self.__free_large_spot_count.update(
              free_spot=self.__free_large_spot_count["free_spot"] + 1
            ),
            ParkingSpotType.MOTORBIKE: self.__free_motorbike_spot_count.update(
              free_spot=self.__free_motorbike_spot_count["free_spot"] + 1
            ),
            ParkingSpotType.ELECTRIC: self.__free_electric_spot_count.update(
              free_spot=self.__free_electric_spot_count["free_spot"] + 1
            ),
        }

        switcher(spot.get_type(), 'Wrong parking spot type!')

