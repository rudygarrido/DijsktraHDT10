"""
    Universidad del Valle de Guatemala
    Algoritmos y Estructuras de Datos
    Seccion 10

                                Hoja de Trabajo No 10

    Rudy Garrido
    Delbert Custodio
    

"""


import networkx as nx
G=nx.DiGraph()
file = open('ciudades.txt', 'r')


"""
Se lee cada linea del archivo de texto, y seguidamente se
usa el espacio entre cada palabra como el delimitador
"""
for line in file:
    words = line.split(" ")
    w2 = len(words[2])
    words[2] = words[2][:w2]
    G.add_edge(words[0], words[1], weight=int(words[2]))

print 'Bienvenido al manejador de ciudades'

#Menu principal
while 1:
    print ''
    print '1. Mostrar la matriz de adyacencia'
    print '2. Mostrar el camino entre ciudades'
    print '3. Eliminar el camino entre ciudades'
    print '4. Agregar camino entre ciudades'
    print '5. Salir'
    try:
        opcion=int(raw_input('Opcion:'))
    except ValueError:
            print "Opcion no valida"
    if opcion==1:
        #Se llama a la funcion nodes() para obtener los nodos del grafo
        print 'Matriz de Adyacencia'
        nodos = G.nodes()
        encabezado = ''
        for nodo in nodos:
            encabezado = encabezado+nodo+'\t'
        print '\t'+encabezado
        #Impresion de nodos
        for nodoActual in nodos:
            linea = ''
            linea = linea + nodoActual + '\t'
            vecinos = G.neighbors(nodoActual)
            for nodo in nodos:
                if nodo in vecinos:
                    linea = linea+str(G[nodoActual][nodo]['weight'])+'\t'
                else:
                    linea = linea + 'Inf\t'
            print linea
    if opcion==2:
        ciudadInicial = raw_input('Ciudad inicial: ')
        ciudadFinal = raw_input('Ciudad final: ')
        try:
            #Se calcula el camino mas corto
            camino = nx.shortest_path(G, source=ciudadInicial, target=ciudadFinal)
            print 'Las ciudades por las que debe pasar son: '   
            #Impresion de la lista conteniendo el camino a seguir            
            print camino
            peso = 0
            posicion =0;
            x = 0
            for x in range(0, len(camino)-1):
                #G[x][y]['weight'] es el peso de la conexion o edge
                peso = peso +  G[camino[x]][camino[x+1]]['weight']
            print 'Con el peso (distancia) de '+str(peso)
        except:
            print 'No hay camino entre estas ciudades'
            

    if opcion ==3:
        #Eliminacion de una conexion entre nodos
        ciudadInicial = raw_input('Ciudad inicial: ')
        ciudadFinal = raw_input('Ciudad final: ')
        try:
            G.remove_edge(ciudadInicial, ciudadFinal)
        except:
             print 'No hay camino entre estas ciudades'   
    if opcion==4:
        #Adicion de una conexion entre nodos
    
        #Sources o nodos a enlazar
        ciudadInicial = raw_input('Ciudad inicial: ')
        ciudadFinal = raw_input('Ciudad final: ')
        try:
            peso=int(raw_input('Peso:'))
        except ValueError:
            print "Numero Invalida"
        G.add_edge(ciudadInicial,ciudadFinal, weight=peso)
        
    
    if opcion==5:
        break

