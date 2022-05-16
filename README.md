# kruger_backend
Challege Kruger

La construccion del aplicativo se trabajo de la siguiente manera:

1) Se creo el paquete Modelo, en el mismo que se agregaron todos los modelos correspondientes con los que se iba a trabajar.

2) Se creo el paquete DTO en el cual se indicaron los DTO a utilizar.

3) Se creo el paquete REPO donde se alojan las clases que utilizarian Jpa.

4) Se creo el paquete Service, para trabajar el CRUD de las clases implicadas.

5) Se creo el paquete ServiceImpl, donde se realizaba la implementacion del service anteriormente mencionado.

6) Por ultimo contamos con el paquete Controller, mismo que se comunica con paqueteria anterior para poder hacer la extraccion/inserccion de la informacion necesaria segun el requerimiento solicitado.

El flujo del presente aplicativo se maneja de la siguiente manera:

1) El Cliente debera obtener un Token, a traves de su usuario y contraseña, mencinado token sera asignado segun el perfil del cliente, basado en este perfil tendra permiso a los correspondientes metodos del servicio.

2) Una vez obtenga el Token, este debera ser enviado al servicio a consumir.

3) Si el Token es un token valido y permitido para el servicio solicitado este retornara la informacion solicitada.

4) El servicio cuenta de 6 rest, los mismos que se detallan a continuacion:

  1.- /oauth/token
    Url que permitira la Obtencion del Token para tener acceso al servicio
    
  2.- /estadovacunacion
   Permitira Crear, listar, modificar, eliminar, los estados de vacunacion. 
   
  3.- /tipovacuna
   Permitira Crear, listar, modificar, eliminar, los tipos de vacunas existentes.
   
  4.- /roles
   Permitira Crear, listar, modificar, eliminar, los tipos de roles existentes.
   
  5.- /usuario
   Permitira Crear, listar, modificar, eliminar, los usuarios correspondientes, al crear un usuario debera indicarse el rol correspondiente al usuario para que sea agregado de forma correcta.
   
  6.- /empleado
   Permitira Crear, listar, modificar, eliminar y buscar por estado de vacunacion, los empleados segun sea el caso.
   
Todos los servicios se encuentran protegidos por permisos de usuarios.
   
