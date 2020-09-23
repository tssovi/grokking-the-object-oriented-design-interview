class Search:
    def search_member(self, name):
        None

    def search_company(self, name):
        None

    def search_job(self, title):
        None


class SearchIndex(Search):
    def __init__(self):
        self.__member_names = {}
        self.__company_names = {}
        self.__job_titles = {}

    def add_member(self, member):
        if member.get_name() in self.__member_names:
            self.__member_names.get(member.get_name()).add(member)
        else:
            self.__member_names[member.get_name()] = member

    def add_company(self, company):
        None

    def add_job_posting(self, job_posting):
        None

    def search_member(self, name):
        return self.__member_names.get(name)

    def search_company(self, name):
        return self.__company_names.get(name)

    def search_job(self, title):
        return self.__job_titles.get(title)
