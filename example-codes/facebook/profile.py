class Profile:
    def __init__(self, profile_picture, cover_photo, gender):
        self.__profile_picture = profile_picture
        self.__cover_photo = cover_photo
        self.__gender = gender

        self.__work_experiences = []
        self.__educations = []
        self.__places = []
        self.__stats = []

    def add_work_experience(self, work):
        None

    def add_education(self, education):
        None

    def add_place(self, place):
        None


class Work:
    def __init__(self, title, company, location, date_from, date_to, description):
        self.__title = title
        self.__company = company
        self.__location = location
        self.__from = date_from
        self.__to = date_to
        self.__description = description
