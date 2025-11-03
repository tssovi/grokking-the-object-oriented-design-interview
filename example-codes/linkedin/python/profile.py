class Profile:
    def __init__(self, summary, experiences, educations, skills, accomplishments, recommendations):
        self.__summary = summary
        self.__experiences = experiences
        self.__educations = educations
        self.__skills = skills
        self.__accomplishments = accomplishments
        self.__recommendations = recommendations
        self.__stats = []

    def add_experience(self, experience):
        None

    def add_education(self, education):
        None

    def add_skill(self, skill):
        None

    def add_accomplishment(self, accomplishment):
        None

    def add_recommendation(self, recommendation):
        None


class Experience:
    def __init__(self, title, company, location, date_from, date_to, description):
        self.__title = title
        self.__company = company
        self.__location = location
        self.__from = date_from
        self.__to = date_to
        self.__description = description
