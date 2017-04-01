
#-*- coding: utf-8 -*-

class TokenType(): # token의 type을 설정해준 class
    ID=3
    INT=2

TOKENTYPE_NAMES={ 2: "INT", 3:"ID"} # key값이 2이면 value는 INT, 3이면 ID

class Token():

    def __init__(self, type, lexeme):
        self.type= type
        self.lexeme= lexeme

    def __str__(self):
        # return self.lexeme
        return "[" + TOKENTYPE_NAMES[self.type] + ": " + self.lexeme + "]"
        # str()함수가 호출되면 key값에 따른 type과 매개변수로 받은 문자열 출력
    def __repr__(self):
        return self.__str__()


class CuteScanner():

    def __init__(self, source):
        source = source.strip() # 문자열 양끝의 공백을 제거하는 기본함수
        # tokenize a string, delimiter is " "
        token_list = source.split(" ") # " "를 기준으로 앞뒤를 나누어 list에 삽입
        # iterator
        self.token_iter = iter(token_list)
        # 첫 token의 주소를 iter변수에 넣어주고 다음 token을 가리킨다

    def next_token(self):
        state=0 # 초기 state는 오토마타 모델에 의해 0
        # get if token exist
        temp_token = next(self.token_iter, None) # 일단 token_iter의 값을 참조하고 다음 주소로 넘어간다

        if temp_token is None :
            return None
        for temp_char in temp_token: # temp_token값을 앞에서부터 char형으로 받아서 반복실행
            """:type : str"""
            if state==0:
                if temp_char.isdigit(): state=2
                elif temp_char=='-': state=1
                elif temp_char.isalpha():state=3
                else :
                    return "ERROR"
            elif state==1:
                if temp_char.isdigit(): state = 2
                else :
                    return "ERROR"
            elif state==2:
                if temp_char.isdigit(): state = 2
                else :
                #if not temp_char.isdigit():
                    return "ERROR"
            elif state==3:
                #if not temp_char.isalpha() and not temp_char.isdigit():
                if temp_char.isalpha() or temp_char.isdigit() : state = 3
                else :
                    return "ERROR"
            else:
                return "ERROR"

        # 오토마타 모델에 의한 state전이 알고리즘
        if state ==2: # 마지막 state가 2이면 temp_token은 숫자
            return Token(TokenType.INT, temp_token)
        elif state==3: # 마지막 state가 3이면 temp_token은 문자
            return Token(TokenType.ID, temp_token)

    def tokenize(self): # 문자열을 token[]에 넣어준다
        #Type is List
        tokens=[]
        while True : # token이 없을 때까지 반복
            temp = self.next_token()
            if temp is None:
                break
            if temp is 'ERROR':
                continue
            tokens.append(temp) # token이 있다면 [VALUE : STRING] 의 형태로 배열에 가장 뒤에 추가
        return tokens

def Test_CuteScanner():
    test_cute = CuteScanner("banana 267 h cat -3789 7 y2010")
    test_tokens=test_cute.tokenize()

    print test_tokens
    for token_i in test_tokens:
        print token_i
    print "end"

Test_CuteScanner()