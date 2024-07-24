# ![LogoUps](src\Resources\Logo.jpg)

**Autor**: Geovanni Zuñiga
**Correo**:[gzunigag@est.ups.edu.ec]
**Fecha de entrega**: 2024-07-24

## Objetivo

El objetivo de este proyecto es encontrar un camino entre un punto de inicio y un punto final, implementando diferentes algoritmos de busqueda incluyendo programacion dinamica, recursividad y estructura de datos que permitan encontrar la solucion.

## Conocimientos a tomar en cuenta

### ¿Que es la programacion dinamica?

La programación dinámica es un método de resolución de problemas que implica dividir un problema en subproblemas más pequeños y resolver cada subproblema solo una vez, almacenando los resultados en una tabla para evitar la repetición de cálculos.

### BFS (Breadth-First Search)

El algoritmo BFS es un algoritmo de búsqueda que explora todos los nodos del nivel actual antes de pasar al siguiente nivel.

### DFS (Depth-First Search)

El algoritmo DFS es un algoritmo de búsqueda que explora tan profundamente como sea posible en un nodo antes de retroceder y explorar otros nodos.

## Solucion

Se implementaron diferentes algoritmos de busqueda para encontrar el camino al punto final. Entre ellos estan:

- Recursiva: Explora cada celda adyacente de forma secuencial marcando cada celda que visita para no volver a pasar por el mismo lugar. Finaliza hasta encontrar el camino.
- Recursiva con Caching: De igual forma explora cada celda adyacente de forma secuencial con la diferencia que guarda en una coleccion las celdas que visita. Finaliza hasta encontrar el camino.
- BFS: Explora y pasa por todos los nodos vecinos antes de pasar al siguiente nivel, al hacer esto puede encontrar el camino mas corto. Ademas de encontrar todos los caminos posibles.
- DFS: Explora hasta llegar al final o no encontrar el camino para luego volver a visitar a los vecinos.

## Resultado final

![ResultadoUI](src\Resources\result.jpg)

## Conclusiones

Al comparar los diferentes algoritmos de busqueda hubo una gran diferencia de resultados al usar BFS, ya que este explora todos los caminos posibles este es el unico que puede encontrar el camino mas corto ademas de ser el más eficiente. Sin embargo cada algoritmo tiene sus ventajas o desventajas que dependen de las restricciones y de que resultado se busca.

## Consideraciones

Este proyecto tiene mucho margen de mejora, desde implementar de una forma mas limpia los algoritmos que creo que es lo mas importante. Tambien mejorar la parte de la interfaz, esta interfaz se hizo sencilla de modo que no tiene muchos detalles y no es muy atractiva.
