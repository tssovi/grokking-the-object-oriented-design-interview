<h1 align="center">Design Stack Overflow</h1>
<h3 align="center">Let's design an online community for developers.</h3>

**We'll cover the following:**

* [System Requirements](#system-requirements)
* [Use Case Diagram](#use-case-diagram)
* [Class Diagram](#class-diagram)
* [Activity Diagram](#activity-diagram)
* [Sequence Diagram](#sequence-diagram)
* [Code](#code)

Stack Overflow is one of the largest online communities for developers to learn and share their knowledge. The website provides a platform for its users to ask and answer questions, and through membership and active participation, to vote questions and answers up or down. Users can edit questions and answers in a fashion similar to a [wiki](https://en.wikipedia.org/wiki/Wiki).

Users of Stack Overflow can earn reputation points and badges. For example, a person is awarded ten reputation points for receiving an “up” vote on an answer and five points for the “up” vote of a question. The can also receive badges for their valued contributions. A higher reputation lets users unlock new privileges like the ability to vote, comment on, and even edit other people’s posts.

<p align="center">
    <img src="/media-files/stack-overflow.jpg" alt="Stack Overflow">
    <br />
    Stack Overflow - Online Community for Developers
</p>

### System Requirements

We will be designing a system with the following requirements:

1. Any non-member (guest) can search and view questions. However, to add or upvote a question, they have to become a member.
2. Members should be able to post new questions.
3. Members should be able to add an answer to an open question.
4. Members can add comments to any question or answer.
5. A member can upvote a question, answer or comment.
6. Members can flag a question, answer or comment, for serious problems or moderator attention.
7. Any member can add a [bounty](https://stackoverflow.com/help/bounty) to their question to draw attention.
8. Members will earn [badges](https://stackoverflow.com/help/badges) for being helpful.
9. Members can vote to [close](https://stackoverflow.com/help/closed-questions) a question; Moderators can close or reopen any question.
10. Members can add [tags](https://stackoverflow.com/help/tagging) to their questions. A tag is a word or phrase that describes the topic of the question.
11. Members can vote to [delete](https://stackoverflow.com/help/deleted-questions) extremely off-topic or very low-quality questions.
12. Moderators can close a question or undelete an already deleted question.
13. The system should also be able to identify most frequently used tags in the questions.

### Use Case Diagram

We have five main actors in our system:

* **Admin:** Mainly responsible for blocking or unblocking members.
* **Guest:** All guests can search and view questions.
* **Member:** Members can perform all activities that guests can, in addition to which they can add/remove questions, answers, and comments. Members can delete and un-delete their questions, answers or comments.
* **Moderator:** In addition to all the activities that members can perform, moderators can close/delete/undelete any question.
* **System:** Mainly responsible for sending notifications and assigning badges to members.

Here are the top use cases for Stack Overflow:

1. Search questions.
2. Create a new question with bounty and tags.
3. Add/modify answers to questions.
4. Add comments to questions or answers.
5. Moderators can close, delete, and un-delete any question.

Here is the use case diagram of Stack Overflow:

<p align="center">
    <img src="/media-files/stack-overflow-use-case.svg" alt="Stack Overflow Use Case Diagram">
    <br />
    Use Case Diagram for Stack Overflow
</p>

### Class Diagram

Here are the main classes of Stack Overflow System:

* **Question:** This class is the central part of our system. It has attributes like Title and Description to define the question. In addition to this, we will track the number of times a question has been viewed or voted on. We should also track the status of a question, as well as closing remarks if the question is closed.
* **Answer:** The most important attributes of any answer will be the text and the view count. In addition to that, we will also track the number of times an answer is voted on or flagged. We should also track if the question owner has accepted an answer.
* **Comment:** Similar to answer, comments will have text, and view, vote, and flag counts. Members can add comments to questions and answers.
* **Tag:** Tags will be identified by their names and will have a field for a description to define them. We will also track daily and weekly frequencies at which tags are associated with questions.
* **Badge:** Similar to tags, badges will have a name and description.
* **Photo:** Questions or answers can have photos.
* **Bounty:** Each member, while asking a question, can place a bounty to draw attention. Bounties will have a total reputation and an expiry date.
* **Account:** We will have four types of accounts in the system, guest, member, admin, and moderator. Guests can search and view questions. Members can ask questions and earn reputation by answering questions and from bounties.
* **Notification:** This class will be responsible for sending notifications to members and assigning badges to members based on their reputations.

<p align="center">
    <img src="/media-files/stack-overflow-class-diagram.svg" alt="Stack Overflow Class Diagram">
    <br />
    Class Diagram for Stack Overflow
</p>

<p align="center">
    <img src="/media-files/stack-overflow-uml.svg" alt="Stack Overflow UML">
    <br />
    UML for Stack Overflow
</p>

### Activity Diagram

**Post a new question:** Any member or moderator can perform this activity. Here are the steps to post a question:

<p align="center">
    <img src="/media-files/stack-overflow-activity-diagram.svg" alt="Stack Overflow Activity Diagram">
    <br />
    Activity Diagram for Stack Overflow
</p>

### Sequence Diagram

Following is the sequence diagram for creating a new question:

<p align="center">
    <img src="/media-files/stack-overflow-sequence-diagram.svg" alt="Stack Overflow Sequence Diagram">
    <br />
    Sequence Diagram for Stack Overflow
</p>

### Code

Here is the high-level definition for the classes described above.

**Enums, data types, and constants:** Here are the required enums, data types, and constants:

```python
from enum import Enum


class QuestionStatus(Enum):
    OPEN, CLOSED, ON_HOLD, DELETED = 1, 2, 3, 4


class QuestionClosingRemark(Enum):
    DUPLICATE, OFF_TOPIC, TOO_BROAD, NOT_CONSTRUCTIVE, NOT_A_REAL_QUESTION, PRIMARILY_OPINION_BASED = 1, 2, 3, 4, 5, 6


class AccountStatus(Enum):
    ACTIVE, CLOSED, CANCELED, BLACKLISTED, BLOCKED = 1, 2, 3, 4, 5


```

**Account, Member, Admin, and Moderator:** These classes represent the different people that interact with our system:

```python
from .constants import *


# For simplicity, we are not defining getter and setter functions. The reader can
# assume that all class attributes are private and accessed through their respective
# public getter methods and modified only through their public methods function.


class Account:
    def __init__(self, id, password, name, address, email, phone, status=AccountStatus.Active):
        self.__id = id
        self.__password = password
        self.__name = name
        self.__address = address
        self.__email = email
        self.__phone = phone
        self.__status = status
        self.__reputation = 0

    def reset_password(self):
        None


class Member:
    def __init__(self, account):
        self.__account = account
        self.__badges = []

    def get_reputation(self):
        return self.__account.get_reputation()

    def get_email(self):
        return self.__account.get_email()

    def create_question(self, question):
        None

    def create_tag(self, tag):
        None


class Admin(Member):
    def block_member(self, member):
        None

    def unblock_member(self, member):
        None


class Moderator(Member):
    def close_question(self, question):
        None

    def undelete_question(self, question):
        None


```

**Badge, Tag, and Notification:** Members have badges, questions have tags and notifications:

```python
from datetime import datetime


class Badge:
    def __init__(self, name, description):
        self.__name = name
        self.__description = description


class Tag:
    def __init__(self, name, description):
        self.__name = name
        self.__description = description
        self.__daily_asked_frequency = 0
        self.__weekly_asked_frequency = 0


class Notification:
    def __init__(self, id, content):
        self.__notification_id = id
        self.__created_on = datetime.datetime.now()
        self.__content = content
    
    def send_notification(self):
        None


```

**Photo and Bounty:** Members can put bounties on questions. Answers and Questions can have multiple photos:

```python
from datetime import datetime

class Photo:
    def __init__(self, id, path, member):
        self.__photo_id = id
        self.__photo_path = path
        self.__creation_date = datetime.now()
        self.__creating_member = member
    
    def delete(self):
        None


class Bounty:
    def __init__(self, reputation, expiry):
        self.__reputation = reputation
        self.__expiry = expiry
    
    def modify_reputation(self, reputation):
        None


```

**Question, Comment and Answer:** Members can ask questions, as well as add an answer to any question. All members can add comments to all open questions or answers:

```python
from datetime import datetime
from abc import ABC
from .constants import *

class Search(ABC):
    def search(self, query):
        None


class Question(Search):
    def __init__(self, title, description, bounty, asking_member):
        self.__title = title
        self.__description = description
        self.__view_count = 0
        self.__vote_count = 0
        self.__creation_time = datetime.now()
        self.__update_time = datetime.now()
        self.__status = QuestionStatus.OPEN
        self.__closing_remark = QuestionClosingRemark.DUPLICATE

        self.__bounty = bounty
        self.__asking_member = asking_member
        self.__photos = []
        self.__comments = []
        self.__answers = []

    def close(self):
        None

    def undelete(self):
        None

    def add_comment(self, comment):
        None

    def add_bounty(self, bounty):
        None

    def search(self, query):
        # return all questions containing the string query in their title or description.
        None


class Comment:
    def __init__(self, text, member):
        self.__text = text
        self.__creation_time = datetime.now()
        self.__flag_count = 0
        self.__vote_count = 0
        self.__asking_member = member

    def increment_vote_count(self):
        None


class Answer:
    def __init__(self, text, member):
        self.__answer_text = text
        self.__accepted = False
        self.__vote_count = 0
        self.__flag_count = 0
        self.__creation_time = datetime.now()
        self.__creating_member = member
        self.__photos = []

    def increment_vote_count(self):
        None


```

