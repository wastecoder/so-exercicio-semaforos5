# 5º Exercício de Semáforos
Permite cálculos simultâneos, mas apenas uma transação com BD por vez

## Descrição do exercício
Um servidor com multiprocessamento recebe requisições que envolve realizar cálculos
e fazer transações com bancos de dados. Por ter uma quantidade grande de núcleos de
processamentos e threads, além de um bom algoritmo de escalonamento de threads,
enquanto as threads fazem cálculos, estes podem ocorrer simultaneamente, mas
quando se faz a transação no banco de dados, esta deve ser apenas uma thread por
vez. Considere um conjunto de threads com IDs definidas na própria aplicação com
números iniciando em 1 e incrementando de um em um.

### As threads tem comportamento como segue:
1) Threads com ID dividido por 3 resultando em resto igual a um fazem as transações:
   - Cálculos de 0,2 a 1,0 segundos
   - Transação de BD por 1 segundo
   - Cálculos de 0,2 a 1,0 segundos
   - Transação de BD por 1 segundo
2) Threads com ID dividido por 3 resultando em resto igual a dois fazem as transações:
   - Cálculos de 0,5 a 1,5 segundos
   - Transação de BD por 1,5 segundo
   - Cálculos de 0,5 a 1,5 segundos
   - Transação de BD por 1,5 segundo
   - Cálculos de 0,5 a 1,5 segundos
   - Transação de BD por 1,5 segundo
3) Threads com ID dividido por 3 resultando em resto igual a zero fazem as transações:
   - Cálculos de 1 a 2 segundos
   - Transação de BD por 1,5 segundo
   - Cálculos de 1 a 2 segundos
   - Transação de BD por 1,5 segundo
   - Cálculos de 1 a 2 segundos
   - Transação de BD por 1,5 segundo
