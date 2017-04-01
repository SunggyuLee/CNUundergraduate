#!/usr/bin/python
#-*- coding: utf-8 -*-

# 한글 사용가능하게 하는 주석

def sum(n): # n까지의 수의 합을 구하는 함수
    if n>1: # n이 1보다 크면 n-1까지의 합을 구한 것에 n을 더해준다
        return n + sum(n-1)
    else: # n이 1이면 합이 1 이므로 1을 return
        return 1
	
def fibonacci(n): # n-1항과 n-2항을 더해서 n항을 만들어주는 함수
    if n>=3: # 일단 n이 1일 경우 f(1)은 1, 2일 경우 f(2)는 2 따라서 3이상 일 때부터 n-1항과 n-2항을 더한 값을 return
        return fibonacci(n-1) + fibonacci(n-2)
    elif n==2:
        return 2
    else:
        return 1

def factorial(n): # 위의 sum함수와 비슷하게 n까지의 합이 아닌 곱을 구하는 함수
    if n>1: # n-1까지의 곱을 구한 것을 n과 곱해서 계산
        return n * factorial(n-1)
    else: # n이 1일 때, 1
        return 1
	
def decimal_to_binary(n): # 10진수를 2진수로 바꾸어 int로 반환하는 함수

    if n != 0: # n이 0이 아니면
        return n%2 + 10 * decimal_to_binary(n/2)
        # n을 2로 나눈 값을 변수로 넣은 함수를 호출하여 10을 곱한 값과 n을 2로 나눈 나머지 값을 return
    return 0 # n이 0일 경우 0을 return

'''    # 반환조건을 int로 해결하기 어려워 일단 만들어본 문자열로 return하는 함수
    if n != 0: # n이 0이 아니면
        return str(decimal_to_binary(n/2)) + str(n%2) # 나머지 값을 뒤쪽에 적어주면서 n/2로 함수호출
                                                     순서가 바뀌면 거꾸로 출력됨
    return "" # 0일 경우 null을 return
'''

def TestRecursionFunction():
    print factorial(7)
    print sum(10)
    print fibonacci(9)
    print decimal_to_binary(10) # 201202149 백승진
    
TestRecursionFunction()
