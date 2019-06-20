+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
   ___     _     ___   ___     _     _  _   ___    ___     _   
  / __|   /_\   / __| / __|   /_\   | \| | |   \  | _ \   /_\  
 | (__   / _ \  \__ \ \__ \  / _ \  | .` | | |) | |   /  / _ \ 
  \___| /_/ \_\ |___/ |___/ /_/ \_\ |_|\_| |___/  |_|_\ /_/ \_\

+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


Este documento describirá el paso a paso de como se implemento el motor de base de datos Cassandra. Esta implementación se realizo en tres nodos en AMAZON AWS, utilizando instancias de tipo EC2, cada una con 4GB de memoria RAM, dos vCPUs y 100GB de disco. Cada nodo va a correr Ubuntu como sistema operativo. La agenda de este documento se describe a continuación.

1. Firewall: Configuración de los grupos de seguridad de las instancias de AMAZON EC2.
2. Implementación de Cassandra.
3. Configurar Cassandra para funcionar con tres nodos diferentes, en regiones apartes.
4. Permitir conexiones remotas.

En este documento damos por un hecho que se sabe como crear un nodo en AMAZON AWS. En caso de que no se tenga conocimiento de como se realiza este proceso, puede seguir las instrucciones del siguiente enlace. 

https://docs.aws.amazon.com/quickstarts/latest/vmlaunch/step-1-launch-instance.html


+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
  ___   _                               _   _ 
 | __| (_)  _ _   ___  __ __ __  __ _  | | | |
 | _|  | | | '_| / -_) \ V  V / / _` | | | | |
 |_|   |_| |_|   \___|  \_/\_/  \__,_| |_| |_|

+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


Para poder acceder a nuestros nodos y además, permitir la comunicación interna entre cada nodo ocupamos habilitar los siguientes puerto en nuestro Security Group de AMAZON EC2. El monitoreo de JMX lo necesitamos para utilizar algunas herramientas de Cassandra.

************************************************************************************************************
PUERTO	PROTOCOLO	DESCRIPCION						ACCESO
7000	TCP		Puerto de comunicación entre nodos. No encriptada.	IP de los nodos del cluster.
7199	TCP		Puerto de monitoreo de JMX.				IP de los nodos del cluster.
9042	TCP		Puerto usado para generar consultas de tipo CQL.	Cualquier IP.
************************************************************************************************************


+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
  ___                  _                              _                 _              
 |_ _|  _ __    _ __  | |  ___   _ __    ___   _ _   | |_   __ _   __  (_)  ___   _ _  
  | |  | '  \  | '_ \ | | / -_) | '  \  / -_) | ' \  |  _| / _` | / _| | | / _ \ | ' \ 
 |___| |_|_|_| | .__/ |_| \___| |_|_|_| \___| |_||_|  \__| \__,_| \__| |_| \___/ |_||_|
               |_|                                                                     

+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


Paso 1. Ingresar a la terminal de uno de los nodos. Donde *KeyName.pem* es la llave generada para ingresar por medio de SSH a la terminal de nuestro nodo y PUBLIC_IP es la IP publica de nuestro nodo.

$ ssh -i KeyName.pem ubuntu@PUBLIC_IP

Paso 2. Crear el archivo de repositorio de Cassandra.

$ echo "deb http://www.apache.org/dist/cassandra/debian 39x main" | sudo tee -a /etc/apt/sources.list.d/cassandra.sources.list

Paso 3. Agregar la llave de GPG. Para evitar problemas con el repositorio, se agregar la llave de GPG y así mejorar su seguridad.

$ curl https://www.apache.org/dist/cassandra/KEYS | sudo apt-key add -

Paso 4. Instalar Cassandra. Primero tenemos que refrescar el repositorio y luego procedemos con la instalación.

$ sudo apt update | sudo apt install cassandra

Paso 5. Verificamos la instalación.

$ sudo systemctl status cassandra

Paso 6. Detenemos Cassandra. Si todo salió bien, vamos a parar la ejecución de Cassandra para realizar las modificaciones necesarias para comunicar nuestros nodos.

$ sudo service cassandra stop

Paso 7. Realizar los pasos anteriores para todos los nodos que vamos a utilizar en el Cluster.


+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
   ___                 __   _                                        _              
  / __|  ___   _ _    / _| (_)  __ _   _  _   _ _   __ _   __   __  (_)  ___   _ _  
 | (__  / _ \ | ' \  |  _| | | / _` | | || | | '_| / _` | / _| / _| | | / _ \ | ' \ 
  \___| \___/ |_||_| |_|   |_| \__, |  \_,_| |_|   \__,_| \__| \__| |_| \___/ |_||_|
                               |___/                                                

+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


Paso 1. Modificar la configuración por defecto de Cassandra. Primero, ingresemos al archivo de configuración de Cassandra, para luego modificar alguna de las configuraciones de este archivo.

$ sudo nano /etc/cassandra/cassandra.yaml

Paso 1.1. Modificar el nombre del cluster.
- cluster_name: [cluster_name]

Paso 1.2. Modificar la configuración de seed_provider. Aquí, en la sección de 'Seeds' tenemos que agregar todos los IP de nuestros nodos.

seeds: "[PUBLIC_IP1], [PUBLIC_IP2], [PUBLIC_IP3]"

Paso 1.3. Modificar la configuración de listen_address. Aquí debemos de colocar el ip privado de la instancia.

listen_address: PRIVATE_ID

Paso 1.4. Agregar una nueva configuración.

broadcast_address: PUBLIC_IP

Paso 2. Eliminar la información ya guardada en Cassandra. Este paso lo corremos para evitar un problema con el nombre del cluster.

$ sudo rm -rf /var/lib/cassandra/data/system/* | sudo rm -rf /usr/lib/cassandra/data/*

Paso 3. Encender el nodo.

Paso 4. Realizar los pasos anteriores para todos los nodos.

Paso 5. Verificar la configuración. Con el siguiente comando se podrá verificar que los nodos se están comunicando.

$ nodetool status


+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
  ___                  _          
 / __|___ _ _  _____ _(_)___ _ _  
| (__/ _ \ ' \/ -_) \ / / _ \ ' \ 
 \___\___/_||_\___/_\_\_\___/_||_|
                                  
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-


Paso 1. Detener el nodo al cual permitiremos acceder remotamente.

$ sudo service Cassandra stop

Paso 2. Modificar la configuración de Cassandra.

$ sudo nano /etc/cassandra/cassandra.yaml

Paso 2.1. Modificar las siguientes configuraciones tal que queden como las siguientes. Nota: broadcast_rpc_address tiene que ser agregada. Además, la configuración de authenticator nos permitirá acceder seguramente.

start_rpc: true
rpc_address: 0.0.0.0
broadcast_rpc_address: PUBLIC_IP
authenticator: PasswordAuthenticator

Paso 3. Ingresar a la consola de cqlsh. Por el momento utilizaremos el usuario por defecto.

$ cqlsh -u cassandra -p cassandra

Paso 4. Crear un nuevo usuario.

CREATE ROLE <nuevo_usuario> WITH PASSWORD = '<contraseña>' 
AND SUPERUSER = true 
AND LOGIN = true;

Paso 5. Ingresar con el nuevo usuario.

$ cqlsh -u nuevo_usuario -p contraseña

Opcionalmente, se puede crear un archivo con la información de autenticación para que cqlsh la obtenga automáticamente. Se deben seguir los siguientes pasos:

Paso 5.1. Crear archivo de autenticación

$ sudo nano ~/.cassandra/cqlshrc

Paso 5.2. Escribir la siguiente información
[authentication]
username = <usuario>
password = <contraseña>

Paso 6. Modificar el usuario por defecto de Cassandra.

ALTER ROLE cassandra WITH PASSWORD='contraseña-diferente'
AND SUPERUSER=false;


+-+-+-+-+-+-+-+-+-+-+-+-+-+-
  ___         _ _
 / __|___  __| (_)__ _ ___
| (__/ _ \/ _` | / _` / _ \
 \___\___/\__,_|_\__, \___/
                 |___/

+-+-+-+-+-+-+-+-+-+-+-+-+-+-


Pueden revisar el código utilizado para la carga de datos y pruebas de escritura en el siguiente repositorio.

https://github.com/Alaincruzc90/Cassandra


+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
 ___                  _ _           _
| _ \___ _ __  ___ __(_) |_ ___ _ _(_)___
|   / -_) '_ \/ _ (_-< |  _/ _ \ '_| / _ \
|_|_\___| .__/\___/__/_|\__\___/_| |_\___/
        |_|

+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-

El repositorio se puede encontrar en la siguiente dirección y utilizando los siguientes credenciales.

Host: ec2-13-59-226-165.us-east-2.compute.amazonaws.com
User: cassandra_user
Password: triton2019exito

Hay una serie de datos que deben tomarse en cuenta al utilizar Cassandra en el repositorio:

Dato 1. Usar ALLOW FILTERING para consultas.
Si se quiere realizar una consulta con SELECT, se debe aplicar la clausula ALLOW FILTERING, así se podrán presentar las columnas clustering con cualquier condición.

Dato 2. Llave de partición y columnas clustering.
La llave de partición le pertenece al nodo, y es responsable por distribuir datos a lo largo de los nodos, esta es igual a una llave primaria de una sola columna.
Las columnas de clustering determinan el orden de los datos en particiones, tienen los datos en una columna de todas las filas.

Dato 3. Uso de ORDER BY.
Cassandra tiene la limitación de que a menos de que se usen restricciones EQ o IN sobre la llave de partición (Lo cual muestra resultados muy limitados), solo puede usar la clausula ORDER BY sobre la llave de partición, pero dado que las tablas ya están ordenadas por el valor de la llave, realmente el unico uso que tiene esta clausula es revertir el orden en que se presentan los datos.

Dato 4. Uso de GROUP BY.
Similar al uso de ORDER BY, GROUP BY solo se puede utilizar sobre la llave de partición.

Dato 5. Soporte de clausulas OFFSET
Cassandra no soporta clausulas offset.

Dato 6. El operador NOT EQUAL no está soportado.
Lo que significa que no se puede aplicar un NOT EQUAL sobre la llave para ordenar todos los datos al usar ORDER BY.

Dato 7. El operador OR no está soportado.
Solo se puede usar AND.
