# -*- coding: utf-8 -*-
from string import letters, digits, whitespace # letters, digits, whitespace 를 이용하기위한 처리


class CuteType:
    INT = 1
    ID = 4

    MINUS = 2
    """-"""
    PLUS = 3
    """+"""

    L_PAREN = 5
    """("""
    R_PAREN = 6
    """)"""

    TRUE = 8
    """#T"""
    FALSE = 9
    """#F"""

    TIMES = 10
    """*"""
    DIV = 11
    """/"""

    LT = 12
    """<"""
    GT = 13
    """>"""
    EQ = 14
    """="""
    APOSTROPHE = 15
    """'"""

    DEFINE = 20
    LAMBDA = 21
    COND = 22
    QUOTE = 23
    NOT = 24
    CAR = 25
    CDR = 26
    CONS = 27
    ATOM_Q = 28
    NULL_Q = 29
    EQ_Q = 30

    KEYWORD_LIST = ('define', 'lambda', 'cond', 'quote', 'not', 'car', 'cdr', 'cons',
                    'atom?', 'null?', 'eq?')


def check_keyword(token):
    """
    :type token:str
    :param token:
    :return:
    """
    if token.lower() in CuteType.KEYWORD_LIST: # 모든 문자를 소문자로 바꾸어 KETWORD_LIST에 있는지 확인
        return True
    return False


def _get_keyword_type(token):
    return {
        'define': CuteType.DEFINE,
        'lambda': CuteType.LAMBDA,
        'cond': CuteType.COND,
        'quote': CuteType.QUOTE,
        'not': CuteType.NOT,
        'car': CuteType.CAR,
        'cdr': CuteType.CDR,
        'cons': CuteType.CONS,
        'atom?': CuteType.ATOM_Q,
        'null?': CuteType.NULL_Q,
        'eq?': CuteType.EQ_Q
    }[token] # switch문과 같은 개념
    # given token의 type number 반환

CUTETYPE_NAMES = dict((eval(attr, globals(), CuteType.__dict__), attr)
for attr in dir(CuteType()) if not callable(attr) and not attr.startswith("__"))
# CUTETYPE_NAMES를 DICTIONARY형으로 만들어준다



class Token(object):
    def __init__(self, type, lexeme):
        """
        :type type:CuteType
        :type lexeme: str
        :param type:
        :param lexeme:
        :return:
        """
        # Initialize the token to generate.
		# Fill Out
        self.type = type
        self.lexeme = lexeme

        if check_keyword(self.lexeme): # lexeme가 keyword list내에 있으면
            self.type = _get_keyword_type(self.lexeme) # keyword의 type number를 가져온다
        print "[" + CUTETYPE_NAMES[self.type] + ": " + self.lexeme + "]"

    def __str__(self):
        # return self.lexeme
        return "[" + CUTETYPE_NAMES[self.type] + ": " + self.lexeme + "]"

    def __repr__(self):
        return str(self)


class Scanner:

    def __init__(self, source_string=None):
        """
        :type self.__source_string: str
        :param source_string:
        """
        self.__source_string = source_string
        self.__pos = 0
        self.__length = len(source_string)
        self.__token_list = []

    def __make_token(self, transition_matrix, build_token_func=None):
        old_state = 0
        self.__skip_whitespace() # whitespace면 무시한다
        temp_char = ""
        return_token = ""
        while not self.eos():
            temp_char = self.get()
            if old_state == 0 and temp_char in (")", "("): # state가 0 이고 char가 ( ) 이면
                return_token = temp_char
                old_state = transition_matrix[(old_state, temp_char)] # state를 matrix과정을 거친 state로 변경
                break

            return_token += temp_char # 조건문에 속하지 않는 경우 return_token에 합쳐준다
            old_state = transition_matrix[(old_state, temp_char)]
            next_char = self.peek() # pos의 크기는 늘어나지 않는다
            if next_char in whitespace or next_char in ("(", ")"): # next_char가 ' '이거나 ( ) 이면 빠져나간다
                break

        return build_token_func(old_state, return_token)

    def scan(self, transition_matrix, build_token_func):
        print "scanning..."
        while not self.eos():
            self.__token_list.append(self.__make_token(
                transition_matrix, build_token_func)) # Token을 list에 넣어준다
        return self.__token_list # [ID : Test] 형식을 되어있는 token들의 list를 반환

    def pos(self):
        return self.__pos

    def eos(self):
        return self.__pos >= self.__length
        # 총 문자열의 길이보다 현재까지 tokenize()한 길이가 크거나 같으면 true를 반환

    def skip(self, pattern):
        while not self.eos(): # 문자열이 끝나지 않은 동안
            temp_char = self.peek() # char하나를 받아온다
            if temp_char in pattern: # 받아온 char가 pattern 내에 있으면
                temp_char = self.get() # 다음 char를 받아온다
            else: # pattern내에 있지않으면 skip()를 빠져나간다
                break

    def __skip_whitespace(self): # whitespace를 무시하는 함수
        self.skip(whitespace)

    def peek(self, length=1): # 하나(length=1이므로)의 char를 확인하는 함수
        return self.__source_string[self.__pos: self.__pos + length]

    def get(self, length=1): # 하나의 char를 가져오게 해주는 함수
        return_get_string = self.peek(length)
        self.__pos += len(return_get_string) # __pos값을 하나 올린다
        return return_get_string


class CuteScanner(object):

    transM = {}

    def __init__(self, source):
        """
        :type source:str
        :param source:
        :return:
        """
        self.source = source
        self._init_TM()

    def _init_TM(self):
        # tranM 초기화 : 각 문자에 해당하는 상태도를 만든다 ex) state가 0일 때, '-'가 읽히면 2로 바꾸어준다
        for alpha in letters:
            self.transM[(0, alpha)] = 4
            self.transM[(4, alpha)] = 4

        for digit in digits:
            self.transM[(0, digit)] = 1
            self.transM[(1, digit)] = 1
            self.transM[(2, digit)] = 1
            self.transM[(4, digit)] = 4

        # Complete the remaining transition matrix
        # Fill Out

        self.transM[(4, '?')] = 16
        self.transM[(0, '-')] = 2
        self.transM[(0, '+')] = 3
        self.transM[(0, '(')] = 5
        self.transM[(0, ')')] = 6
        self.transM[(0, '#')] = 7
        self.transM[(7, 'T')] = 8
        self.transM[(7, 'T')] = 9

        self.transM[(0, '*')] = 10
        self.transM[(0, '/')] = 11
        self.transM[(0, "'")] = 15
        self.transM[(0, '>')] = 13
        self.transM[(0, '=')] = 14
        self.transM[(0, '<')] = 12

    def tokenize(self):
        print "  ===  tokenize  === "

        def build_token(type, lexeme): return Token(type, lexeme) # Token을 return하는 함수
        print self.source
        cute_scanner = Scanner(self.source)
        return cute_scanner.scan(self.transM, build_token)
print globals()
test_cute = CuteScanner("Test car + ' - * #T ( ) eq?")
test_tokens = test_cute.tokenize()
print test_tokens
