#include <iostream>

int main() {
  std::cout << (-5 % 2) << std::endl;
  return 0;
  // To run
  // g++ modulo.cpp -o modulo && ./modulo
  // PRINTS -1
  // C++ performs modulo operations using truncated division
}
