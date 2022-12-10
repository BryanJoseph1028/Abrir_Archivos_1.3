package analizador;
import static analizador.Token.*;
%%
%class Lexer
%type Token
//C = [""]
L = [a-zA-Z_]
D = [0-9]

WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}

"=" {lexeme=yytext();return ASSIGN;}
"+" {lexeme=yytext();return SUMA;}
"*" {lexeme=yytext();return MULT;}
"-" {lexeme=yytext();return RESTA;}
"/" {lexeme=yytext();return DIV;}
"¡"|"!" {lexeme=yytext(); return EXCLAMACION;}
"#" {lexeme=yytext(); return NUMERAL;}



"$" {lexeme=yytext(); return $;}
"MDS" {lexeme=yytext(); return MDS;}
"ECHO" {lexeme=yytext(); return ECHO;}
"int" {lexeme=yytext(); return INT;}
"MAIN" {lexeme=yytext(); return MAIN;}
"("|")" {lexeme=yytext(); return PARENTESIS;}
"{"|"}" {lexeme=yytext(); return LLAVE;}
"GETt" {lexeme=yytext(); return GET;}
"<<"|">>" {lexeme=yytext(); return OPERACIONALES;}
"'" {lexeme=yytext(); return COMILLAS;}
"endl" {lexeme=yytext(); return ENDL;}
";" {lexeme=yytext(); return PUNTO_COMA;}
"getch" {lexeme=yytext(); return GETCH;}
   


//{C} {lexeme=yytext(); return COMILLAS;}
{L}({L}|{D})* {lexeme=yytext(); return CADENA;}
 ("(-"{D}+")")|{D}+ {lexeme=yytext(); return NUM;}
. {return ERROR;}