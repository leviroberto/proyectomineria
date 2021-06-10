-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 18, 2019 at 09:35 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdsistemayersica`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `AdelantoPersonal_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_Agregar_sp` (IN `contrato` INT, IN `fecha` DATE, IN `soles` DECIMAL(9,2), IN `motivo` VARCHAR(250), IN `empresa` INT)  NO SQL
INSERT INTO `adelanto_personal`( `Contrato_Id`, `AdelantoPersonal_Fecha`, `AdelantoPersonal_Motivo`, `AdelantoPersonal_Monto`, `Empresa_Id`) VALUES (contrato,fecha,motivo,soles,empresa)$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
ap.Contrato_Id,
ap.Estado_Id

from adelanto_personal as ap WHERE ap.AdelantoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from adelanto_personal WHERE AdelantoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_ModificarAdelantoPagoPersonal_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AdelantoPersonal_ModificarAdelantoPagoPersonal_sp` (IN `codigo` INT, IN `pagoPersonal` INT)  NO SQL
UPDATE adelanto_personal as ap set ap.Estado_Id=7,ap.PagoPersonal_Id=pagoPersonal WHERE ap.AdelantoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_ModificarAPagado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_ModificarAPagado_sp` (IN `codigo` INT)  NO SQL
UPDATE adelanto_personal as ap set ap.Estado_Id=7 WHERE ap.AdelantoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_ModificarPorPagar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_ModificarPorPagar_sp` (IN `codigo` INT)  NO SQL
UPDATE adelanto_personal as ap set ap.Estado_Id=8 WHERE ap.AdelantoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_Modificar_sp` (IN `contrato` INT, IN `fecha` DATE, IN `monto` DECIMAL(9,2), IN `motivo` VARCHAR(250), IN `codigo` INT)  NO SQL
UPDATE adelanto_personal as ap set ap.AdelantoPersonal_Fecha=fecha,
ap.AdelantoPersonal_Motivo=motivo,
ap.AdelantoPersonal_Monto=monto,
ap.Contrato_Id=contrato
WHERE ap.AdelantoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_mostrarAdelantoPersonalPorContrato_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_mostrarAdelantoPersonalPorContrato_sp` (IN `contrato` INT, IN `estado` INT)  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion

from adelanto_personal as ap INNER JOIN estado as e on e.Estado_Id=ap.Estado_Id 
WHERE ap.Contrato_Id=contrato and ap.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_mostrarAdelantoPersonalPorPago_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_mostrarAdelantoPersonalPorPago_sp` (IN `codigo` INT)  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion

from adelanto_personal as ap INNER JOIN estado as 
e on e.Estado_Id=ap.Estado_Id 
INNER JOIN pago_personal as pt on pt.PagoPersonal_Id=ap.PagoPersonal_Id
WHERE pt.PagoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_MostrarAdelantosTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_MostrarAdelantosTodosPorLike_sp` (IN `texto` VARCHAR(50))  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion,
p.Personal_Id,
co.Contrato_Id,
co.Contrato_Sueldo,
concat(p.Personal_Apellidos,' ',p.Personal_Nombres) as personal
from adelanto_personal as ap INNER JOIN estado as e on e.Estado_Id=ap.Estado_Id
INNER JOIN contrato as co on co.Contrato_Id=ap.Contrato_Id INNER JOIN personal as p on p.Personal_Id=co.Personal_Id WHERE concat(p.Personal_Nombres,p.Personal_Apellidos,p.Personal_DNI) LIKE concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_MostrarAdelantosTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_MostrarAdelantosTodos_sp` ()  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion,
p.Personal_Id,
co.Contrato_Id,
co.Contrato_Sueldo,
concat(p.Personal_Apellidos,' ',p.Personal_Nombres) as personal
from adelanto_personal as ap INNER JOIN estado as e on e.Estado_Id=ap.Estado_Id
INNER JOIN contrato as co on co.Contrato_Id=ap.Contrato_Id INNER JOIN personal as p on p.Personal_Id=co.Personal_Id$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_MostrarTodosPorLikeGrupo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_MostrarTodosPorLikeGrupo_sp` (IN `texto` VARCHAR(50))  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion,
p.Personal_Id,
co.Contrato_Id,
co.Contrato_Sueldo,
sum(ap.AdelantoPersonal_Monto) as total,
concat(p.Personal_Apellidos,' ',p.Personal_Nombres) as personal
from adelanto_personal as ap INNER JOIN estado as e on e.Estado_Id=ap.Estado_Id
INNER JOIN contrato as co on co.Contrato_Id=ap.Contrato_Id INNER JOIN personal as p on p.Personal_Id=co.Personal_Id where ap.Estado_Id=8 and concat(p.Personal_Nombres,p.Personal_Apellidos,p.Personal_DNI) like concat('%',texto,'%') GROUP by p.Personal_Id$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_MostrarTodosPorLike_sp` (IN `texto` VARCHAR(250))  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion,
p.Personal_Id,
co.Contrato_Id,
co.Contrato_Sueldo,
concat(p.Personal_Apellidos,' ',p.Personal_Nombres) as personal
from adelanto_personal as ap INNER JOIN estado as e on e.Estado_Id=ap.Estado_Id
INNER JOIN contrato as co on co.Contrato_Id=ap.Contrato_Id INNER JOIN personal as p on p.Personal_Id=co.Personal_Id WHERE concat(p.Personal_Nombres,p.Personal_Apellidos,p.Personal_DNI) like concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_MostrarTodos_sp` ()  NO SQL
SELECT ap.AdelantoPersonal_Id,
ap.AdelantoPersonal_Fecha,
ap.AdelantoPersonal_Motivo,
ap.AdelantoPersonal_Monto,
e.Estado_Descripcion,
p.Personal_Id,
co.Contrato_Id,
co.Contrato_Sueldo,
sum(ap.AdelantoPersonal_Monto) as total,
concat(p.Personal_Apellidos,' ',p.Personal_Nombres) as personal
from adelanto_personal as ap INNER JOIN estado as e on e.Estado_Id=ap.Estado_Id
INNER JOIN contrato as co on co.Contrato_Id=ap.Contrato_Id INNER JOIN personal as p on p.Personal_Id=co.Personal_Id where ap.Estado_Id=8 GROUP by p.Personal_Id$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_ObtenerTotalSolesPorPersonal_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_ObtenerTotalSolesPorPersonal_sp` (IN `contrato` INT)  NO SQL
SELECT 
sum(ap.AdelantoPersonal_Monto) as total

from adelanto_personal as ap WHERE ap.Contrato_Id=contrato and ap.Estado_Id=8$$

DROP PROCEDURE IF EXISTS `AdelantoPersonal_verificarSiHayPagosDisponibles_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `AdelantoPersonal_verificarSiHayPagosDisponibles_sp` (IN `contrato` INT)  NO SQL
SELECT 
count(ap.AdelantoPersonal_Id) as total
from adelanto_personal as ap WHERE ap.Estado_Id=8 and ap.Contrato_Id=contrato$$

DROP PROCEDURE IF EXISTS `Adelanto_AdelantoPorCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_AdelantoPorCliente_sp` (IN `nombre` VARCHAR(200))  NO SQL
Select 
a.Adelanto_Id,
a.Adelanto_Moneda,
a.Adelanto_Cantidad,
a.Adelanto_Descripcion,
a.Adeelanto_Estado,
DATE_FORMAT(a.Adelanto_Fecha, '%d/%m/%Y'),
CONCAT(c.ClienteEntrante_Apellidos,' ',c.ClienteEntrante_Nombres) as cliente,
c.ClienteEntrante_DNI
from adelanto as a inner join cliente_entrante as c on a.ClienteEntrante_Id=c.ClienteEntrante_Id 
where CONCAT(c.ClienteEntrante_Apellidos,' ',c.ClienteEntrante_Nombres) like concat('%',nombre,'%') 
GROUP by  
CONCAT(c.ClienteEntrante_Apellidos,' ',c.ClienteEntrante_Nombres),
c.ClienteEntrante_DNI,
a.Adelanto_Id,
a.Adelanto_Moneda,
a.Adelanto_Cantidad,
a.Adelanto_Descripcion,
a.Adeelanto_Estado,
DATE_FORMAT(a.Adelanto_Fecha, '%d/%m/%Y') ORDER BY a.Adelanto_Fecha DESC$$

DROP PROCEDURE IF EXISTS `Adelanto_AgregarAdelantoCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_AgregarAdelantoCliente_sp` (IN `cantidad` DECIMAL(9,2), IN `moneda` CHAR(50), IN `descripcion` VARCHAR(200), IN `fecha` DATE, IN `cliente` INT, IN `personal` INT, IN `empresa` INT, IN `estado` VARCHAR(50))  NO SQL
INSERT INTO `adelanto`( `Adelanto_Cantidad`, `Adelanto_Moneda`, `Adelanto_Descripcion`,  `Adelanto_Fecha`, `ClienteEntrante_Id`, `Personal_Id`,Empresa_id,Adeelanto_Estado) VALUES (cantidad,moneda,descripcion,fecha,cliente,personal,empresa,estado)$$

DROP PROCEDURE IF EXISTS `Adelanto_AgregarAdelantoProveedor_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_AgregarAdelantoProveedor_sp` (IN `cantidad` DECIMAL(9,2), IN `moneda` CHAR(50), IN `descripcion` VARCHAR(200), IN `fecha` DATE, IN `proveedor` INT, IN `personal` INT, IN `estadoAd` VARCHAR(50), IN `empresas` INT)  NO SQL
INSERT INTO `adelanto`( `Adelanto_Cantidad`, `Adelanto_Moneda`, `Adelanto_Descripcion`,  `Adelanto_Fecha`, `ProveedorServicio_Id`, `Personal_Id`,Adeelanto_Estado,Empresa_id) VALUES (cantidad,moneda,descripcion,fecha,proveedor,personal,estadoAd,empresas)$$

DROP PROCEDURE IF EXISTS `Adelanto_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_Agregar_sp` (IN `cantidad` DECIMAL(9,2), IN `moneda` CHAR(50), IN `descripcion` VARCHAR(200), IN `fecha` DATE, IN `cliente` INT, IN `proveedor` INT, IN `personal` INT, IN `estadoAd` VARCHAR(50), IN `empresas` INT)  NO SQL
INSERT INTO `adelanto`( `Adelanto_Cantidad`, `Adelanto_Moneda`, `Adelanto_Descripcion`,  `Adelanto_Fecha`, `ClienteEntrante_Id`, `ProveedorServicio_Id`, `Personal_Id`,Adeelanto_Estado,Empresa_id) VALUES (cantidad,moneda,descripcion,fecha,cliente,proveedor,proveedor,personal,estadoAd,empresas)$$

DROP PROCEDURE IF EXISTS `Adelanto_BuscarPorCodigoCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_BuscarPorCodigoCliente_sp` (IN `codigo` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
a.ClienteEntrante_Id,
a.Adeelanto_EStado
from adelanto as a WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_BuscarPorCodigoProveedor_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_BuscarPorCodigoProveedor_sp` (IN `codigo` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
a.ProveedorServicio_Id,
a.Adeelanto_EStado
from adelanto as a WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 

a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
a.ClienteEntrante_Id,
a.ProveedorServicio_Id,
a.Adelanto_Estado
from adelanto as a WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_CalcularTotalNoPagadoDolaresCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_CalcularTotalNoPagadoDolaresCliente_sp` (IN `estados` VARCHAR(50), IN `moneda` CHAR(50), IN `cliente` INT)  NO SQL
SELECT sum(a.Adelanto_Cantidad) as totalNoPagado from adelanto as a WHERE a.Adeelanto_Estado=estados and a.Adelanto_Moneda=moneda and a.ClienteEntrante_Id=cliente$$

DROP PROCEDURE IF EXISTS `Adelanto_CalcularTotalNoPagadoDolaresProveedor_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_CalcularTotalNoPagadoDolaresProveedor_sp` (IN `estados` VARCHAR(50), IN `moneda` CHAR(50), IN `proveedor` INT)  NO SQL
SELECT sum(a.Adelanto_Cantidad) as totalNoPagado from adelanto as a WHERE a.Estado_Id=8 and a.Adeelanto_Estado=estados and a.Adelanto_Moneda=moneda and a.ProveedorServicio_Id=proveedor$$

DROP PROCEDURE IF EXISTS `Adelanto_CalcularTotalNoPagadoSolesCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_CalcularTotalNoPagadoSolesCliente_sp` (IN `estados` VARCHAR(50), IN `moneda` VARCHAR(50), IN `cliente` INT)  NO SQL
SELECT sum(a.Adelanto_Cantidad) as totalNoPagado from adelanto as a WHERE a.Adeelanto_Estado=estados  and a.Adelanto_Moneda=moneda and a.ClienteEntrante_Id=cliente$$

DROP PROCEDURE IF EXISTS `Adelanto_CalcularTotalNoPagadoSolesProveedor_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_CalcularTotalNoPagadoSolesProveedor_sp` (IN `estados` VARCHAR(50), IN `moneda` CHAR(50), IN `proveedor` INT)  NO SQL
SELECT sum(a.Adelanto_Cantidad) as totalNoPagado from adelanto as a WHERE a.Estado_Id=8 and a.Adeelanto_Estado=estados and a.Adelanto_Moneda=moneda and a.ProveedorServicio_Id=proveedor$$

DROP PROCEDURE IF EXISTS `Adelanto_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from adelanto  WHERE Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_ModificarCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_ModificarCliente_sp` (IN `cantidad` DECIMAL(9,2), IN `moneda` CHAR(50), IN `descripcion` VARCHAR(200), IN `fecha` DATE, IN `cliente` INT, IN `personals` INT, IN `estadoA` VARCHAR(50), IN `codigo` INT)  NO SQL
UPDATE adelanto as a SET 
a.Adelanto_Cantidad=cantidad,
a.Adelanto_Moneda=moneda,
a.Adelanto_Descripcion=descripcion,
a.Adelanto_Fecha=fecha,
a.ClienteEntrante_Id=cliente,
a.Personal_Id=personals,
a.Adeelanto_Estado=estadoA
WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_ModificarEstadoNoPagado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_ModificarEstadoNoPagado_sp` (IN `estado` VARCHAR(50), IN `codigo` INT)  NO SQL
UPDATE adelanto as a SET a.Adeelanto_Estado=estado WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_ModificarProveedor_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_ModificarProveedor_sp` (IN `cantidad` DECIMAL(9,2), IN `moneda` CHAR(50), IN `descripcion` VARCHAR(200), IN `fecha` DATE, IN `proveedor` INT, IN `personal` INT, IN `estadoA` VARCHAR(50), IN `codigo` INT)  NO SQL
UPDATE adelanto as a SET 
a.Adelanto_Cantidad=cantidad,
a.Adelanto_Moneda=moneda,
a.Adelanto_Descripcion=descripcion,
a.Adelanto_Fecha=fecha,
a.ProveedorServicio_Id=proveedor,
a.Personal_Id=personal,
a.Adeelanto_Estado=estadoA
WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_Modificar_sp` (IN `cantidad` DECIMAL(9,2), IN `moneda` CHAR(50), IN `descripcion` VARCHAR(200), IN `fecha` DATE, IN `cliente` INT, IN `proveedor` INT, IN `personals` INT, IN `estadoA` VARCHAR(50), IN `codigo` INT)  NO SQL
UPDATE adelanto as a SET a.Adelanto_Cantidad=cantidad,
a.Adelanto_Moneda=moneda,
a.Adelanto_Descripcion=descripcion,
a.Adelanto_Fecha=fehca,
a.ClienteEntrante_Id=cliente,
a.ProveedorServicio_Id=proveedor,
a.Personal_Id=personals,
a.Adelanto_Estado=estado
WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_MosdificarValorizacionAdelanto_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MosdificarValorizacionAdelanto_sp` (IN `estado` VARCHAR(20), IN `valorizacionId` INT, IN `codigo` INT)  NO SQL
UPDATE adelanto as a SET a.Adeelanto_Estado=estado,a.Valorizacion_Id=valorizacionId WHERE a.Adelanto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarAdelantoPorClienteValorizacion_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarAdelantoPorClienteValorizacion_sp` (IN `valorizacionid` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
a.Adeelanto_Estado

from adelanto as a WHERE a.Valorizacion_Id=valorizacionid$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosClientePagadoNoPagadoPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosClientePagadoNoPagadoPorLike_sp` (IN `estado` INT, IN `cliente` INT, IN `codigo` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
concat(ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) as ClienteEntrante,
a.Adeelanto_Estado

from adelanto as a INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=a.ClienteEntrante_Id
WHERE a.ClienteEntrante_Id=cliente and a.Estado_Id=estado and 
a.Adelanto_Id like concat('%',codigo,'%') order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosClientePagadoNoPagado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosClientePagadoNoPagado_sp` (IN `codigo` INT, IN `codigocli` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
concat(ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) as ClienteEntrante,
a.Adeelanto_Estado

from adelanto as a INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=a.ClienteEntrante_Id
WHERE a.ClienteEntrante_Id=codigocli and a.Estado_Id=codigo order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosClientePorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosClientePorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
concat(ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) as ClienteEntrante,
a.Adeelanto_Estado

from adelanto as a INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=a.ClienteEntrante_Id
WHERE  a.Estado_Id=estado and a.Adeelanto_Estado="No PAGADO" and concat(ce.ClienteEntrante_DNI,ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) like concat('%',texto,'%') order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosCliente_sp` (IN `codigocli` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres) as ClienteEntrante,
a.Adeelanto_Estado

from adelanto as a INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=a.ClienteEntrante_Id
WHERE a.ClienteEntrante_Id=codigocli and a.Adeelanto_Estado='NO PAGADO'  order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosProveedorPagadoNoPagadoPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosProveedorPagadoNoPagadoPorLike_sp` (IN `estado` INT, IN `proveedor` INT, IN `codigo` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
ps.ProveedorServicio_Razon_Social,
a.Adeelanto_Estado

from adelanto as a INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=a.ProveedorServicio_Id

WHERE a.ProveedorServicio_Id=proveedor and a.Estado_Id=estado and a.Adelanto_Id  like concat('%',codigo,'%') order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosProveedorPagadoNoPagado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosProveedorPagadoNoPagado_sp` (IN `codigo` INT, IN `codigopro` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
ps.ProveedorServicio_Razon_Social,
a.Adeelanto_Estado

from adelanto as a INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=a.ProveedorServicio_Id
WHERE a.ProveedorServicio_Id=codigopro and a.Estado_Id=codigo order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosProveedorPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosProveedorPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
ps.ProveedorServicio_Razon_Social,
a.Adeelanto_Estado

from adelanto as a INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=a.ProveedorServicio_Id
WHERE  a.Estado_Id=8 and a.Adeelanto_Estado="NO PAGADO" and concat(ps.ProveedorServicio_Ruc,ps.ProveedorServicio_Razon_Social) like concat('%',texto,'%') order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodosProveedor_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodosProveedor_sp` (IN `codigo` INT, IN `codigopro` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
ps.ProveedorServicio_Razon_Social,
a.Adeelanto_Estado

from adelanto as a INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=a.ProveedorServicio_Id
WHERE a.ProveedorServicio_Id=codigopro and a.Estado_Id=codigo and a.Adeelanto_Estado="NO PAGADO" order by a.Adelanto_Fecha desc$$

DROP PROCEDURE IF EXISTS `Adelanto_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Adelanto_MostrarTodos_sp` (IN `codigo` INT)  NO SQL
SELECT 
a.Adelanto_Id,
a.Adelanto_Cantidad,
a.Adelanto_Moneda,
a.Adelanto_Descripcion,
a.Adelanto_Fecha,
concat(ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) as ClienteEntrante,
ps.ProveedorServicio_Razon_Social

from adelanto as a INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=a.ClienteEntrante_Id
INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=a.ProveedorServicio_Id
WHERE a.Estado_Id=codigo$$

DROP PROCEDURE IF EXISTS `AgrupadorFE_InsertarNumeroAgrupador_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AgrupadorFE_InsertarNumeroAgrupador_sp` (IN `numero` INT, IN `fecha` VARCHAR(50))  NO SQL
INSERT INTO agrupadorfe(AgrupadorFE_Orden,AgrupadorFE_Fecha) VALUES(numero,fecha)$$

DROP PROCEDURE IF EXISTS `AgrupadorFE_ObtenerAgrupador_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AgrupadorFE_ObtenerAgrupador_sp` (IN `fecha` VARCHAR(50))  NO SQL
SELECT max(f.AgrupadorFE_Orden) as orden,f.AgrupadorFE_Fecha from agrupadorfe as f WHERE  f.AgrupadorFE_Fecha=fecha$$

DROP PROCEDURE IF EXISTS `AgrupadorNCE_InsertarNumeroAgrupador_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AgrupadorNCE_InsertarNumeroAgrupador_sp` (IN `numero` INT, IN `fecha` VARCHAR(50))  NO SQL
INSERT INTO agrupadornce(AgrupadorNCE_Orden,AgrupadoNCE_Fecha) VALUES(numero,fecha)$$

DROP PROCEDURE IF EXISTS `AgrupadorNCE_ObtenerAgrupador_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AgrupadorNCE_ObtenerAgrupador_sp` ()  NO SQL
SELECT f.AgrupadorNCE_Orden,f.AgrupadoNCE_Fecha from agrupadornce as f WHERE f.AgrupadoNCE_Id=(SELECT max(agr.AgrupadoNCE_Id)   from agrupadornce as agr)$$

DROP PROCEDURE IF EXISTS `AgrupadorNDE_InsertarNumeroAgrupador_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AgrupadorNDE_InsertarNumeroAgrupador_sp` (IN `numero` INT, IN `fecha` VARCHAR(100))  NO SQL
INSERT INTO agrupadornde(AgrupadorNDE_Orden,AgrupadoNDE_Fecha) VALUES(numero,fecha)$$

DROP PROCEDURE IF EXISTS `AgrupadorNDE_ObtenerAgrupador_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `AgrupadorNDE_ObtenerAgrupador_sp` ()  NO SQL
SELECT f.AgrupadorNDE_Orden,f.AgrupadoNDE_Fecha from agrupadornde as f WHERE f.AgrupadorNDE_Id=(SELECT max(agr.AgrupadorNDE_Id)   from agrupadornde as agr)$$

DROP PROCEDURE IF EXISTS `Cambio_Actualizar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cambio_Actualizar_sp` (IN `dolar` DECIMAL(9,2), IN `Tarifa` DECIMAL(9,2), IN `Tarifaa` DECIMAL(9,2), IN `Trans1` DECIMAL(9,2), IN `Trans2` DECIMAL(9,2), IN `Poli` DECIMAL(9,2), IN `Tonelada` DECIMAL(9,2), IN `codigo` INT)  NO SQL
UPDATE `cambios` SET 
`Cambios_Dolar`=dolar
,`Cambios_Tarifa`=Tarifa
,`Cambios_Tarifaa`=Tarifaa
,`Cambios_Trans1`=Trans1
,`Cambios_Trans2`=Trans2
,`Cambios_Poli`=Poli
,`Cambios_Tonelada`=Tonelada
WHERE `Cambios_Id`=codigo$$

DROP PROCEDURE IF EXISTS `Cambio_ActulizarDolar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cambio_ActulizarDolar_sp` (IN `dolar` DECIMAL(9,2))  NO SQL
UPDATE cambios set Cambios_Dolar=dolar where Cambios_Id=1$$

DROP PROCEDURE IF EXISTS `Cambio_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cambio_Agregar_sp` (IN `dolar` DECIMAL(9,2), IN `poli` DECIMAL(9,2), IN `tarifa` DECIMAL(9,2), IN `tarifaa` DECIMAL(9,2), IN `tonelada` DECIMAL(9,2), IN `trans1` DECIMAL(9,2), IN `trans2` DECIMAL(9,2))  NO SQL
INSERT INTO `cambios`(`Cambios_Dolar`, `Cambios_Tarifa`, `Cambios_Tarifaa`, `Cambios_Trans1`, `Cambios_Trans2`, `Cambios_Poli`, `Cambios_Tonelada`) VALUES (dolar,tarifa,tarifaa,trans1,trans2,poli,tonelada)$$

DROP PROCEDURE IF EXISTS `Cambio_LlenarCambio_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cambio_LlenarCambio_sp` ()  NO SQL
SELECT `Cambios_Id`, `Cambios_Dolar`, `Cambios_Tarifa`, `Cambios_Tarifaa`, `Cambios_Trans1`, `Cambios_Trans2`, `Cambios_Poli`, `Cambios_Tonelada` FROM `cambios`$$

DROP PROCEDURE IF EXISTS `Cambio_MostrarDolar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cambio_MostrarDolar_sp` (IN `estado` INT)  NO SQL
SELECT `Cambios_Dolar` FROM `cambios` where  Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `Cambio_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cambio_MostrarTodos_sp` (IN `estados` INT)  NO SQL
SELECT `Cambios_Id`, `Cambios_Dolar`, `Cambios_Tarifa`, `Cambios_Tarifaa`, `Cambios_Trans1`, `Cambios_Trans2`, `Cambios_Poli`, `Cambios_Tonelada` FROM `cambios` WHERE Estado_Id=estados$$

DROP PROCEDURE IF EXISTS `Cheque_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_Agregar_sp` (IN `proveedor` INT, IN `concepto` VARCHAR(400), IN `entregado` VARCHAR(100), IN `monto` DECIMAL(9,2), IN `lectura` VARCHAR(250), IN `moneda` CHAR(8), IN `femision` VARCHAR(50), IN `fpago` VARCHAR(50), IN `empresas` INT, IN `personal` INT)  NO SQL
INSERT INTO cheque(ProveedorServicio_Id,Cheque_Concepto,Cheque_EntregadoA,
                  Cheque_Monto,
                   Cheque_Lectura,
                   Cheque_Moneda,
                   Cheque_FechaEmision,
                   Cheque_FechaPago,
                   Empresa_Id,
                   Personal_Id
                  )VALUES(proveedor,concepto,entregado,monto,lectura,moneda,
                         femision,fpago,empresas,personal
                         )$$

DROP PROCEDURE IF EXISTS `Cheque_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
ch.Cheque_Id,
ch.Cheque_EntregadoA,
ch.Cheque_Concepto,
ch.Cheque_Monto,
ch.Cheque_Moneda,
ch.Cheque_FechaPago,
ch.Cheque_FechaEmision,
ch.ProveedorServicio_Id

FROM cheque as ch WHERE ch.Cheque_Id=codigo$$

DROP PROCEDURE IF EXISTS `Cheque_CalcularTotalDolar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_CalcularTotalDolar_sp` (IN `estado` INT)  NO SQL
SELECT sum(c.Cheque_Monto) as totals from cheque as c WHERE c.Estado_Id=estado and c.Cheque_Moneda='$'$$

DROP PROCEDURE IF EXISTS `Cheque_CalcularTotalSoles_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_CalcularTotalSoles_sp` (IN `estado` INT)  NO SQL
SELECT sum(c.Cheque_Monto) as total from cheque as c WHERE c.Estado_Id=estado and c.Cheque_Moneda='S/.'$$

DROP PROCEDURE IF EXISTS `Cheque_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from cheque WHERE Cheque_Id=codigo$$

DROP PROCEDURE IF EXISTS `Cheque_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_Modificar_sp` (IN `prove` INT, IN `concep` VARCHAR(400), IN `entregado` VARCHAR(100), IN `monto` DECIMAL(9,2), IN `lectura` VARCHAR(250), IN `moneda` CHAR(8), IN `femision` VARCHAR(50), IN `fpago` VARCHAR(50), IN `personals` INT, IN `codigo` INT)  NO SQL
UPDATE cheque as c SET c.ProveedorServicio_Id=prove,
c.Cheque_Concepto=concep,
c.Cheque_EntregadoA=entregado,
c.Cheque_Monto=monto,
c.Cheque_Lectura=lectura,
c.Cheque_Moneda=moneda,
c.Cheque_FechaEmision=femision,
c.Cheque_FechaPago=fpago,
c.Personal_Id=personals
WHERE c.Cheque_Id=codigo$$

DROP PROCEDURE IF EXISTS `Cheque_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT 
ch.Cheque_Id,
ch.Cheque_EntregadoA,
ch.Cheque_Concepto,
ch.Cheque_Monto,
ch.Cheque_Moneda,
ch.Cheque_FechaPago,
ch.Cheque_FechaEmision,
ps.ProveedorServicio_Razon_Social

FROM cheque as ch INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=ch.ProveedorServicio_Id WHERE ch.Estado_Id=estado and concat(ps.ProveedorServicio_Razon_Social) like concat('%',texto,'%')
ORDER by  ch.Cheque_Id DESC$$

DROP PROCEDURE IF EXISTS `Cheque_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT 
ch.Cheque_Id,
ch.Cheque_EntregadoA,
ch.Cheque_Concepto,
ch.Cheque_Monto,
ch.Cheque_Moneda,
ch.Cheque_FechaPago,
ch.Cheque_FechaEmision,
ps.ProveedorServicio_Razon_Social

FROM cheque as ch INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=ch.ProveedorServicio_Id WHERE ch.Estado_Id=estado
ORDER by  ch.Cheque_Id DESC$$

DROP PROCEDURE IF EXISTS `Cheque_ObtenerUltimoCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Cheque_ObtenerUltimoCodigo_sp` (IN `estado` INT)  NO SQL
SELECT max(c.Cheque_Id) as ultimo  from cheque as c WHERE c.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_Agregar_sp` (IN `nombre` VARCHAR(64), IN `apellido` VARCHAR(128), IN `tipoCliente` INT, IN `telefono` CHAR(15), IN `sexo` CHAR(1), IN `personal` INT, IN `dni` CHAR(8), IN `direccion` VARCHAR(200), IN `empresa` INT)  NO SQL
INSERT INTO cliente_entrante(
                             ClienteEntrante_DNI,
                             ClienteEntrante_Apellidos,				
                             ClienteEntrante_Nombres,
                             ClienteEntrante_Telefono,
                             ClienteEntrante_Direccion,
                             ClienteEntrante_Sexo, 
                             TipoCliente_Id,
                             Personal_Id,
							Empresa_Id
) VALUES(dni,apellido,nombre,telefono,direccion,sexo,tipoCliente,personal,empresa)$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT ce.ClienteEntrante_Id,
ce.ClienteEntrante_DNI,
ce.ClienteEntrante_Apellidos,
ce.ClienteEntrante_Nombres,
ce.ClienteEntrante_Telefono,
ce.ClienteEntrante_Direccion,
ce.ClienteEntrante_Sexo,
ce.TipoCliente_Id
from cliente_entrante as ce where ce.ClienteEntrante_Id=codigo$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_BuscarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_BuscarTodosPorLike_sp` (IN `dni` CHAR(8))  NO SQL
SELECT ce.ClienteEntrante_Id, ce.ClienteEntrante_DNI,   concat(ce.ClienteEntrante_Apellidos ," ", ce.ClienteEntrante_Nombres) as nombre,ce.ClienteEntrante_Telefono, ce.ClienteEntrante_Sexo, ce.TipoCliente_Id,concat(p.Personal_Apellidos, p.Personal_Nombres) as personal,
tc.TipoCliente_Descripcion
FROM cliente_entrante as ce INNER join personal as p on p.Personal_Id=ce.Personal_Id INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=ce.TipoCliente_Id WHERE ce.Estado_Id=1   and CONCAT(ce.ClienteEntrante_DNI,ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) LIKE concat('%',dni,'%')$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_Eliminar_sp` (IN `codigo` INT)  NO SQL
UPDATE cliente_entrante as ce SET ce.Estado_Id=3 WHERE ce.ClienteEntrante_Id=codigo$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_Modificar_sp` (IN `nombre` VARCHAR(64), IN `apellido` VARCHAR(128), IN `tipoCliente` INT, IN `telefono` VARCHAR(15), IN `sexo` CHAR(1), IN `persona` INT, IN `dni` INT, IN `direccion` VARCHAR(200), IN `codigo` INT)  NO SQL
UPDATE cliente_entrante as ce SET ce.ClienteEntrante_Nombres=nombre,
ce.ClienteEntrante_Apellidos=apellido,
ce.TipoCliente_Id=tipoCliente,
ce.ClienteEntrante_Telefono=telefono,
ce.ClienteEntrante_Sexo=sexo,
ce.Personal_Id=persona,
ce.ClienteEntrante_DNI=dni,
ce.ClienteEntrante_Direccion=direccion
WHERE ce.ClienteEntrante_Id=codigo$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT ce.ClienteEntrante_Id, ce.ClienteEntrante_DNI,   concat(ce.ClienteEntrante_Apellidos ," ", ce.ClienteEntrante_Nombres) as nombre,ce.ClienteEntrante_Telefono, ce.ClienteEntrante_Sexo, ce.TipoCliente_Id,concat(p.Personal_Apellidos, p.Personal_Nombres) as personal,
tc.TipoCliente_Descripcion
FROM cliente_entrante as ce INNER join personal as p on p.Personal_Id=ce.Personal_Id INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=ce.TipoCliente_Id WHERE ce.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `ClienteEntrante_obtenerClientePorDefectoParaLiquidacion_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClienteEntrante_obtenerClientePorDefectoParaLiquidacion_sp` (IN `estado` INT)  NO SQL
SELECT ce.ClienteEntrante_Id,
ce.ClienteEntrante_DNI,
ce.ClienteEntrante_Apellidos,
ce.ClienteEntrante_Nombres,
ce.ClienteEntrante_Telefono,
ce.ClienteEntrante_Direccion,
ce.ClienteEntrante_Sexo,
ce.TipoCliente_Id
from cliente_entrante as ce where ce.Estado_Id=estado and concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres)='POR CAMBIAR'$$

DROP PROCEDURE IF EXISTS `Concepto_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Concepto_Agregar_sp` (IN `concepto` CHAR(64), IN `personals` INT)  NO SQL
INSERT INTO concepto(Concepto_Descripcion,Personal_Id) VALUES(concepto,personals)$$

DROP PROCEDURE IF EXISTS `Concepto_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Concepto_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT c.Concepto_Id,c.Concepto_Descripcion  from concepto as c WHERE c.Concepto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Concepto_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Concepto_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from concepto WHERE Concepto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Concepto_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Concepto_Modificar_sp` (IN `codigo` INT, IN `descripcion` VARCHAR(64), IN `personals` INT)  NO SQL
UPDATE concepto as c set c.Concepto_Descripcion=descripcion,c.Personal_Id=personals WHERE c.Concepto_Id=codigo$$

DROP PROCEDURE IF EXISTS `Concepto_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Concepto_MostrarTodos_sp` (IN `estados` INT)  NO SQL
SELECT c.Concepto_Id,c.Concepto_Descripcion from concepto as c WHERE c.Estado_Id=estados$$

DROP PROCEDURE IF EXISTS `Contrato_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_Agregar_sp` (IN `sueldo` DECIMAL(9,2), IN `totalDias` INT, IN `finicio` DATE, IN `ffin` DATE, IN `personal` INT, IN `empresda` INT)  NO SQL
INSERT INTO `contrato`(`Personal_Id`, `Contrato_FechaContrato`, `Contrato_FechaFin`, `Contrato_TotalDiasPagar`, `Contrato_Sueldo`, `Empresa_Id`)VALUES(personal,finicio,ffin,totalDias,sueldo,empresda)$$

DROP PROCEDURE IF EXISTS `Contrato_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
c.Contrato_Id,
c.Personal_Id,
c.Contrato_FechaContrato,
c.Contrato_FechaFin,
c.Contrato_TotalDiasPagar,
c.Contrato_Sueldo,
c.Estado_Id

from contrato as c WHERE c.Contrato_Id=codigo$$

DROP PROCEDURE IF EXISTS `Contrato_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from contrato WHERE Contrato_Id=codigo$$

DROP PROCEDURE IF EXISTS `Contrato_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_Modificar_sp` (IN `sueldo` DECIMAL(9,2), IN `totald` INT, IN `finicio` DATE, IN `ffin` DATE, IN `personal` INT, IN `codigo` INT)  NO SQL
UPDATE contrato as c set c.Personal_Id=personal,
c.Contrato_FechaContrato=finicio,
c.Contrato_FechaFin=ffin,
c.Contrato_TotalDiasPagar=totald,
c.Contrato_Sueldo=sueldo
WHERE c.Contrato_Id=codigo$$

DROP PROCEDURE IF EXISTS `Contrato_MostrarTodosPorPersonalPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_MostrarTodosPorPersonalPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT c.Contrato_Id,
c.Personal_Id,
c.Contrato_FechaContrato,
c.Contrato_FechaFin,
c.Contrato_TotalDiasPagar,
c.Contrato_Sueldo,
e.Estado_Descripcion
from contrato as c INNER JOIN estado as e on e.Estado_Id=c.Estado_Id INNER JOIN personal as p on p.Personal_Id=c.Personal_Id WHERE c.Estado_Id=estado and concat(p.Personal_Nombres,p.Personal_Apellidos,p.Personal_DNI) LIKE concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `Contrato_MostrarTodosPorPersonal_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_MostrarTodosPorPersonal_sp` (IN `codigo` INT)  NO SQL
SELECT c.Contrato_Id,
c.Personal_Id,
c.Contrato_FechaContrato,
c.Contrato_FechaFin,
c.Contrato_TotalDiasPagar,
c.Contrato_Sueldo,
e.Estado_Descripcion
from contrato as c INNER JOIN estado as e on e.Estado_Id=c.Estado_Id WHERE c.Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `Contrato_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_MostrarTodos_sp` ()  NO SQL
SELECT c.Contrato_Id,
c.Personal_Id,
c.Contrato_FechaContrato,
c.Contrato_FechaFin,
c.Contrato_TotalDiasPagar,
c.Contrato_Sueldo,
e.Estado_Descripcion
from contrato as c INNER JOIN estado as e on e.Estado_Id=c.Estado_Id WHERE c.Estado_Id=4 order by c.Contrato_FechaFin$$

DROP PROCEDURE IF EXISTS `Contrato_obtenerListaContratoPersonal_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_obtenerListaContratoPersonal_sp` ()  NO SQL
SELECT 
c.Contrato_Id,
c.Personal_Id,
c.Contrato_FechaContrato,
c.Contrato_FechaFin,
c.Contrato_TotalDiasPagar,
c.Contrato_Sueldo,
c.Estado_Id

from contrato as c WHERE c.Estado_Id=4$$

DROP PROCEDURE IF EXISTS `Contrato_VerificarSiTieneContratoPersonal_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Contrato_VerificarSiTieneContratoPersonal_sp` (IN `codigo` INT)  NO SQL
SELECT c.Contrato_Id,
c.Personal_Id,
c.Contrato_FechaContrato,
c.Contrato_FechaFin,
c.Contrato_TotalDiasPagar,
c.Contrato_Sueldo,
c.Estado_Id

from contrato as c WHERE c.Estado_Id=4 and c.Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `CorreFactura_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `CorreFactura_MostrarTodos_sp` ()  NO SQL
SELECT fc.CorreFactura_Id,ps.ProveedorServicio_Razon_Social,fac.Factura_Nro from correo_factura as fc INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=fc.ProveedorServicio_Id INNER JOIN factura_electronica as fe on fe.FacturaElectronica_Id=fc.FacturaElectronica_Id
INNER JOIN factura as fac on fac.Factura_Id=fe.Factura_Id$$

DROP PROCEDURE IF EXISTS `CorreoFactura_Agregar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `CorreoFactura_Agregar_sp` (IN `ProveedorServicio_Id` INT, IN `FacturaElectronica_Id` INT, IN `descripcion` VARCHAR(500))  NO SQL
INSERT INTO correo_factura(ProveedorServicio_Id,CorreoFactura_Descripcion,FacturaElectronica_Id) VALUES(ProveedorServicio_Id,descripcion,FacturaElectronica_Id)$$

DROP PROCEDURE IF EXISTS `CorreoFactura_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `CorreoFactura_MostrarTodos_sp` ()  NO SQL
SELECT correo.CorreFactura_Id,ps.ProveedorServicio_Razon_Social,f.Factura_Nro from correo_factura as correo INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=correo.ProveedorServicio_Id INNER JOIN factura_electronica as fe on fe.FacturaElectronica_Id=correo.FacturaElectronica_Id INNER JOIN factura as f on f.Factura_Id=fe.Factura_Id$$

DROP PROCEDURE IF EXISTS `DetallePermisosPersonal_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DetallePermisosPersonal_Modificar_sp` (IN `codigo` INT, IN `estado` INT)  NO SQL
UPDATE detalle_permiso_personal as dp set dp.Estado_Id=estado WHERE dp.DetallePermisoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `DetallePermisosPersonal_MostrarPorPersonal_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DetallePermisosPersonal_MostrarPorPersonal_sp` (IN `codigo` INT)  NO SQL
SELECT dt.DetallePermisoPersonal_Id ,
p.Permisos_Descripcion, 
p.Permisos_Id,
e.Estado_Id,
e.Estado_Descripcion from detalle_permiso_personal  as dt INNER JOIN permisos as p on p.Permisos_Id=dt.Permisos_Id 
INNER JOIN estado as e on e.Estado_Id=dt.Estado_Id WHERE dt.Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `Empresa_buscarPorCodigo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Empresa_buscarPorCodigo` (IN `codigo` INT)  NO SQL
SELECT 
e.Empresa_id,
e.Empresa_Departamento,
e.Empresa_Nombre_Comercial,
e.Empresa_NombreBD
from empresa as e WHERE e.Empresa_id=codigo$$

DROP PROCEDURE IF EXISTS `Empresa_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Empresa_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
em.Empresa_id,
em.Empresa_Departamento,
em.Empresa_Direccion,
em.Empresa_Nombre_Comercial,
em.Empresa_Distrito,
em.Empresa_Nombre_Legal,
em.Empresa_Nro_Documento,
em.Empresa_Tipo_Documento,
em.Empresa_Ubigeo,
em.Empresa_Urbanizacion,
em.Empresa_Provincia,
em.Empresa_RutaPFX,
em.Empresa_ClavePFX,
em.Empresa_RutaXML,
em.Empresa_Mision,
em.Empresa_Vision,
em.Empresa_Telefono,
em.Empresa_Fecha_Registrado,
em.Empresa_RutaIImagen,
em.Empresa_CodigoLocal,
em.Empresa_NombreBD,
em.Empresa_UsuarioSunat,
em.Empresa_PasswordSunat,
em.Empresa_EndPointUrl,
em.Empresa_signature,
em.Empresa_RutaXMLNotaDebito,
em.Empresa_RutaXMLNotaCredito
from empresa as em WHERE em.Empresa_id=codigo$$

DROP PROCEDURE IF EXISTS `Empresa_ObtenerImagen`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Empresa_ObtenerImagen` ()  NO SQL
SELECT e.Empresa_RutaIImagen from empresa as e$$

DROP PROCEDURE IF EXISTS `EstadoFacturaElectronica_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `EstadoFacturaElectronica_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT 
es.EstadoFacturaElectronica_Id,
es.EstadoFacturaElectronica_Descripcion
from estado_factura_electronica as es WHERE es.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `Estado_Actualizar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Estado_Actualizar_sp` (IN `codigo` INT, IN `descripcion` VARCHAR(80))  NO SQL
UPDATE estado SET Estado_Descripcion=descripcion where Estado_Id=codigo$$

DROP PROCEDURE IF EXISTS `Estado_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Estado_Agregar_sp` (IN `descripcion` VARCHAR(80))  NO SQL
insert into estado (Estado_Descripcion) VALUES(descripcion)$$

DROP PROCEDURE IF EXISTS `Estado_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Estado_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT e.Estado_Id ,e.Estado_Descripcion from estado as e WHERE e.Estado_Id=codigo$$

DROP PROCEDURE IF EXISTS `Estado_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Estado_MostrarTodos_sp` ()  NO SQL
SELECT e.Estado_Id ,e.Estado_Descripcion   FROM estado as e$$

DROP PROCEDURE IF EXISTS `FacturaDetalle_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaDetalle_Agregar_sp` (IN `cantidad` VARCHAR(20), IN `unidad` CHAR(50), IN `descripcion` VARCHAR(200), IN `punitario` DECIMAL(9,2), IN `importe` CHAR(50), IN `facturas` INT, IN `adelantos` VARCHAR(20), IN `personal` INT, IN `liquidaciones` INT)  NO SQL
INSERT INTO `factura_detalle`( `FacturaDetalle_Cantidad`, `FacturaDetalle_Unidad`, `FacturaDetalle_Descripcion`, `FacturaDetalle_Precio_Unitario`, `FacturaDetalle_Importe`, `Factura_Id`, `FacturaDetalle_Adelanto`, `Personal_Id`,Liquidacion_Id) VALUES (cantidad,unidad,descripcion,punitario,importe,facturas,adelantos,personal,liquidaciones)$$

DROP PROCEDURE IF EXISTS `FacturaDetalle_BuscarPorCodigoFacturaParaFE_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaDetalle_BuscarPorCodigoFacturaParaFE_sp` (IN `codigo` INT)  NO SQL
BEGIN
 set @TipoPrecio='01';
 set @Impuesto='18';
 set @TipoImpueto='10';
 set @ImpuestoSelectivo='0';
 set @OtroImpuesto='0';
 set @Unidad='TNE';
  
SELECT fd.FacturaDetalle_Id,
CASE WHEN fd.FacturaDetalle_Cantidad="" THEN '1' ELSE fd.FacturaDetalle_Cantidad END as cantidad,
 @Unidad as unidad,
 CASE WHEN fd.FacturaDetalle_Importe="" THEN 0 else fd.FacturaDetalle_Importe end as Suma,
 CASE WHEN fd.FacturaDetalle_Precio_Unitario="" THEN '1' ELSE fd.FacturaDetalle_Precio_Unitario end as PrecioUnitario,
@TipoPrecio as TipoPrecio,
@Impuesto as Impuesto,
@TipoImpueto as TipoImpueto,
@ImpuestoSelectivo as ImpuestoSelectivo,
@OtroImpuesto as   OtroImpuesto,
fd.FacturaDetalle_Descripcion as Descripcion
FROM factura_detalle as fd
where fd.Factura_Id=codigo;
 
END$$

DROP PROCEDURE IF EXISTS `FacturaDetalle_BuscarPorFactura_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaDetalle_BuscarPorFactura_sp` (IN `codigo` INT)  NO SQL
SELECT 
fd.FacturaDetalle_Id,
fd.FacturaDetalle_Cantidad,
fd.FacturaDetalle_Unidad,
fd.FacturaDetalle_Descripcion,
fd.FacturaDetalle_Precio_Unitario,
fd.FacturaDetalle_Importe,
fd.FacturaDetalle_Adelanto,
fd.Liquidacion_Id
from factura_detalle as fd WHERE fd.Factura_Id=codigo and fd.Estado_Id=1$$

DROP PROCEDURE IF EXISTS `FacturaDetalle_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaDetalle_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from factura_detalle WHERE FacturaDetalle_Id=codigo$$

DROP PROCEDURE IF EXISTS `FacturaDetalle_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaDetalle_Modificar_sp` (IN `cantidad` VARCHAR(20), IN `unidad` CHAR(50), IN `descripcion` VARCHAR(200), IN `punitario` DECIMAL(9,2), IN `importe` CHAR(50), IN `facturas` INT, IN `adelantos` VARCHAR(20), IN `personal` INT, IN `liquidaciones` INT, IN `codigo` INT)  NO SQL
UPDATE factura_detalle set  `FacturaDetalle_Cantidad`=cantidad, `FacturaDetalle_Unidad`=unidad, `FacturaDetalle_Descripcion`=descripcion, `FacturaDetalle_Precio_Unitario`=punitario, `FacturaDetalle_Importe`=importe, `Factura_Id`=facturas, `FacturaDetalle_Adelanto`=adelantos, `Personal_Id`=personal,Liquidacion_Id=liquidaciones WHERE FacturaDetalle_Id=codigo$$

DROP PROCEDURE IF EXISTS `FacturaElectronica_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaElectronica_Agregar_sp` (IN `Factura_Ids` INT, IN `RutaXML` VARCHAR(1000), IN `fechaGeneracion` VARCHAR(100), IN `estadoFE` INT)  NO SQL
INSERT INTO factura_electronica(Factura_Id,FacturaElectronica_RutaXML,
                               FacturaElectronica_FechaGeneracionArchivo,
                              EstadoFacturaElectronica_Id)VALUES(Factura_Ids,
                                                              RutaXML,
                                                                fechaGeneracion,
                                                                 estadoFE
                                                                )$$

DROP PROCEDURE IF EXISTS `FacturaElectronica_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaElectronica_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
fe.FacturaElectronica_FechaGeneracionArchivo
from factura_electronica as fe WHERE fe.Factura_Id=codigo$$

DROP PROCEDURE IF EXISTS `FacturaElectronica_ModificarDeBaja_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaElectronica_ModificarDeBaja_sp` (IN `facturas` INT, IN `estado` INT, IN `fechabaja` VARCHAR(100), IN `rutaxml` VARCHAR(1024))  NO SQL
UPDATE factura_electronica as fe set fe.FacturaElectronica_RutaXMLBaja=rutaxml,
fe.FacturaElectronica_FechaDeBaja=fechabaja,
fe.EstadoFacturaElectronica_Id=estado
WHERE fe.Factura_Id=facturas$$

DROP PROCEDURE IF EXISTS `FacturaElectronica_ModificarEnvio_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaElectronica_ModificarEnvio_sp` (IN `facturas` INT, IN `rutaxml` VARCHAR(1000), IN `fechag` VARCHAR(50), IN `estados` INT)  NO SQL
UPDATE factura_electronica as fe SET fe.FacturaElectronica_RutaXML=rutaxml,
fe.FacturaElectronica_FechaGeneracionArchivo=fechag,
fe.EstadoFacturaElectronica_Id=estados
WHERE fe.Factura_Id=facturas$$

DROP PROCEDURE IF EXISTS `FacturaElectronica_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaElectronica_Modificar_sp` (IN `Factura_Ids` INT, IN `estadoFE` INT, IN `FechaDeBajaArchivo` VARCHAR(100), IN `RutaXMLBaja` VARCHAR(1000))  NO SQL
UPDATE factura_electronica as fe SET fe.EstadoFacturaElectronica_Id=estado,fe.FacturaElectronica_RutaGeneracionBaja=FechaDeBajaArchivo,
fe.FacturaElectronica_RutaXMLBaja=RutaXMLBaja WHERE fe.Factura_Id=Factura_Ids$$

DROP PROCEDURE IF EXISTS `FacturaElectronica_MostrarFacturas_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaElectronica_MostrarFacturas_sp` (IN `estado` INT, IN `fechaInicio` VARCHAR(100), IN `fechaFin` VARCHAR(100))  NO SQL
SELECT 
f.Factura_Id,
f.Factura_Nro,
f.Factura_Total,
f.Factura_Fecha,
fe.FacturaElectronica_Id
from factura_electronica as fe INNER JOIN factura as f on f.Factura_Id=fe.Factura_Id WHERE fe.EstadoFacturaElectronica_Id=estado and f.Factura_Fecha BETWEEN fechaInicio and fechaFin ORDER by f.Factura_Fecha$$

DROP PROCEDURE IF EXISTS `FacturaElectronico_BuscarParaCorreo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `FacturaElectronico_BuscarParaCorreo_sp` (IN `codigo` INT)  NO SQL
SELECT fe.FacturaElectronica_Id,fe.FacturaElectronica_RutaXML,fe.FacturaElectronica_RutaPDF,f.ProveedorServicio_Id ,f.Factura_Nro from factura_electronica as fe

INNER JOIN factura as f on f.Factura_Id=fe.Factura_Id
WHERE f.Factura_Id=codigo$$

DROP PROCEDURE IF EXISTS `FacturaElectronico_ModificarRutaODF_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `FacturaElectronico_ModificarRutaODF_sp` (IN `codigo` INT, IN `rutapdf` VARCHAR(1024))  NO SQL
UPDATE factura_electronica as fe set fe.FacturaElectronica_RutaPDF=rutapdf WHERE fe.Factura_Id=codigo$$

DROP PROCEDURE IF EXISTS `FacturaNumero_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaNumero_Modificar_sp` (IN `Empresa_ID` INT, IN `NumeroCorrelativo` INT)  NO SQL
UPDATE  factura_numero as fn SET fn.FacturaNumero_Correlativo=NumeroCorrelativo
where fn.FacturaNumero_Id=3$$

DROP PROCEDURE IF EXISTS `FacturaNumero_ObtenerCorrelativo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaNumero_ObtenerCorrelativo_sp` (IN `Empresa_Id` INT)  NO SQL
SELECT  MAX(fn.FacturaNumero_Correlativo) as correlativo from factura_numero as fn where fn.Empresa_Id=Empresa_Id$$

DROP PROCEDURE IF EXISTS `FacturaNumero_ObtenerSerie_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `FacturaNumero_ObtenerSerie_sp` (IN `Empresa_Id` INT)  NO SQL
SELECT fn.FacturaNumero_Serio as serie  from factura_numero as fn where fn.Empresa_Id=Empresa_Id$$

DROP PROCEDURE IF EXISTS `Factura_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_Agregar_sp` (IN `nroFactura` VARCHAR(50), IN `proveedor` INT, IN `fecha` DATE, IN `guian` CHAR(50), IN `direccion` VARCHAR(200), IN `valor` DECIMAL(9,2), IN `igv` DECIMAL(9,2), IN `total` DECIMAL(9,2), IN `descripcion` VARCHAR(50), IN `moneda` CHAR(5), IN `codigoUn` CHAR(250), IN `rnc` CHAR(30), IN `lectura` VARCHAR(500), IN `empresa` INT, IN `personal` INT, IN `codigo` INT)  NO SQL
INSERT INTO `factura`( `Factura_Nro`, `ProveedorServicio_Id`, `Factura_Fecha`, `Factura_Guian`, `Factura_Direccion`, `Factura_ValorVenta`, `Factura_IGV`, `Factura_Total`, `Factura_Descripcion`, `Factura_Moneda`, `Factura_CodigoUnico`, `Factura_RNC`, `Factura_Lectura`,  `Empresa_Id`, `Personal_Id`,Factura_Id) VALUES(nroFactura,proveedor,fecha,guian,direccion,valor,igv,total,descripcion,
moneda,codigoUn,rnc,lectura,empresa,personal,codigo)$$

DROP PROCEDURE IF EXISTS `Factura_BuscarPorCodigoParaFE_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_BuscarPorCodigoParaFE_sp` (IN `codigo` INT)  NO SQL
SELECT
f.Factura_Id,
f.Factura_Nro,
ps.ProveedorServicio_Id,
ps.ProveedorServicio_Razon_Social,
f.Factura_Fecha,
f.Factura_Guian,
f.Factura_IGV,
f.Factura_Total,
f.Factura_Descripcion,
f.Factura_Moneda,
f.Factura_Lectura,
f.Factura_FechaPago,
em.Empresa_Nro_Documento,
f.Factura_Fecha_Registrado,
f.Empresa_Id,
f.Factura_ValorVenta
from factura as f INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=f.ProveedorServicio_Id INNER JOIN empresa as em on em.Empresa_id=f.Empresa_Id WHERE f.Factura_Id=codigo$$

DROP PROCEDURE IF EXISTS `Factura_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
f.Factura_Id,
f.Factura_Nro,
f.ProveedorServicio_Id,
f.Factura_Fecha,
f.Factura_Guian,
f.Factura_Direccion,
f.Factura_Moneda,
f.Factura_CodigoUnico,
f.Factura_Descripcion,
f.Factura_Lectura,
f.Factura_IGV,
f.Factura_Total,
f.Factura_ValorVenta
from factura as f WHERE f.Factura_Id=codigo$$

DROP PROCEDURE IF EXISTS `Factura_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_Modificar_sp` (IN `nroFactura` VARCHAR(50), IN `proveedor` INT, IN `fecha` DATE, IN `guian` CHAR(50), IN `direccion` VARCHAR(200), IN `valor` DECIMAL(9,2), IN `igv` DECIMAL(9,2), IN `total` DECIMAL(9,2), IN `descripcion` VARCHAR(50), IN `moneda` CHAR(5), IN `codigoUn` CHAR(250), IN `rnc` CHAR(30), IN `lectura` VARCHAR(500), IN `empresa` INT, IN `personals` INT, IN `codigo` INT)  NO SQL
UPDATE factura  set Factura_Nro=nroFactura, ProveedorServicio_Id=proveedor, Factura_Fecha=fecha, Factura_Guian=guian, Factura_Direccion=direccion, Factura_ValorVenta=valor, Factura_IGV=igv, Factura_Total=total, Factura_Descripcion=descripcion, Factura_Moneda=moneda,Factura_CodigoUnico=codigoUn, Factura_RNC=rnc, Factura_Lectura=lectura,  Empresa_Id=empresa, Personal_Id=personals WHERE Factura_Id=codigo$$

DROP PROCEDURE IF EXISTS `Factura_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT 
f.Factura_Id,
f.Factura_Nro,
ps.ProveedorServicio_Razon_Social,
f.Factura_Fecha,
f.Factura_Guian,
f.Factura_ValorVenta,
f.Factura_IGV,
f.Factura_Total,
f.Factura_Moneda,
f.Factura_CodigoUnico,
e.Empresa_Nombre_Comercial
from factura as f INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=f.ProveedorServicio_Id
INNER JOIN empresa as e on e.Empresa_id=f.Empresa_Id where
f.Estado_Id=estado 

and concat(f.Factura_Nro,ps.ProveedorServicio_Razon_Social,ps.ProveedorServicio_Ruc) like concat('%',texto,'%')
ORDER by f.Factura_Nro DESC$$

DROP PROCEDURE IF EXISTS `Factura_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT 
f.Factura_Id,
f.Factura_Nro,
ps.ProveedorServicio_Razon_Social,
f.Factura_Fecha,
f.Factura_Guian,
f.Factura_ValorVenta,
f.Factura_IGV,
f.Factura_Total,
f.Factura_Moneda,
f.Factura_CodigoUnico,
e.Empresa_Nombre_Comercial
from factura as f INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=f.ProveedorServicio_Id
INNER JOIN empresa as e on e.Empresa_id=f.Empresa_Id where
f.Estado_Id=estado ORDER by f.Factura_Nro DESC$$

DROP PROCEDURE IF EXISTS `Factura_ObtenerUltimoCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Factura_ObtenerUltimoCodigo_sp` ()  NO SQL
select max(fa.Factura_Id) as ultimaFactura from factura as fa$$

DROP PROCEDURE IF EXISTS `GastosExtras_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GastosExtras_Agregar_sp` (IN `descripcion` VARCHAR(250), IN `monto` DECIMAL(9,2), IN `cliente` INT, IN `concepto` INT, IN `moneda` VARCHAR(5), IN `personal` INT)  NO SQL
INSERT INTO `gastos_extras`(`GastosExtras_Descripcion`, `GastosExtras_Monto`, `ClienteEntrante_Id`, `Concepto_Id`, `GastosExtras_Moneda`,  `Personal_Id`) VALUES (descripcion,monto,cliente,concepto,moneda,personal)$$

DROP PROCEDURE IF EXISTS `GastosExtras_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GastosExtras_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
ge.GastosExtras_Id,
ge.GastosExtras_Descripcion,
ge.GastosExtras_Monto,
ge.ClienteEntrante_Id,
ge.Concepto_Id,
ge.GastosExtras_Moneda

from gastos_extras as ge WHERE ge.GastosExtras_Id=codigo$$

DROP PROCEDURE IF EXISTS `GastosExtras_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GastosExtras_Eliminar_sp` (IN `codigo` INT)  NO SQL
UPDATE gastos_extras SET Estado_Id=3 WHERE GastosExtras_Id=codigo$$

DROP PROCEDURE IF EXISTS `GastosExtras_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GastosExtras_Modificar_sp` (IN `descripcion` VARCHAR(250), IN `monto` DECIMAL(9,2), IN `cliente` INT, IN `concepto` INT, IN `moneda` VARCHAR(5), IN `personal` INT, IN `codigo` INT)  NO SQL
UPDATE gastos_extras as ge set ge.GastosExtras_Descripcion=descripcion,
ge.GastosExtras_Monto=monto,
ge.ClienteEntrante_Id=cliente,
ge.Concepto_Id=concepto,
ge.GastosExtras_Moneda=moneda,
ge.Personal_Id=personal
WHERE ge.GastosExtras_Id=codigo$$

DROP PROCEDURE IF EXISTS `GastosExtras_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GastosExtras_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT 
g.GastosExtras_Id,
g.GastosExtras_Descripcion,
g.GastosExtras_Monto,
concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres) as clienteEntrante,
c.Concepto_Descripcion,
g.GastosExtras_Fecha_Registrado

from gastos_extras as g INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=g.ClienteEntrante_Id 

INNER JOIN concepto as c on c.Concepto_Id=g.Concepto_Id
WHERE g.Estado_Id=estado and concat(ce.ClienteEntrante_DNI,ce.ClienteEntrante_Apellidos,ce.ClienteEntrante_Nombres) like concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `GastosExtras_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GastosExtras_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT 
g.GastosExtras_Id,
g.GastosExtras_Descripcion,
g.GastosExtras_Monto,
concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres) as clienteEntrante,
c.Concepto_Descripcion,
DATE_FORMAT(g.GastosExtras_Fecha_Registrado,'%Y-%m-%d') as fecha

from gastos_extras as g INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=g.ClienteEntrante_Id 

INNER JOIN concepto as c on c.Concepto_Id=g.Concepto_Id
WHERE g.Estado_Id=estado ORDER by g.GastosExtras_Fecha_Registrado DESC$$

DROP PROCEDURE IF EXISTS `Liquidacion_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_Agregar_sp` (IN `fecha` DATE, IN `clienteEn` INT, IN `proce` INT, IN `lote` TEXT, IN `tmh` TEXT, IN `h2o` TEXT, IN `tms` TEXT, IN `leyau` TEXT, IN `leyag` TEXT, IN `inter` TEXT, IN `rec` TEXT, IN `maquilla` TEXT, IN `factor` TEXT, IN `conscn` TEXT, IN `escalador` TEXT, IN `stms` TEXT, IN `estado` TEXT, IN `reintegro` TEXT, IN `facturado` TEXT, IN `totalus` TEXT, IN `personal` INT, IN `empresa` INT)  NO SQL
INSERT INTO `liquidacion`(`Procedencia_Id`, `Liquidacion_Fecha`,
                          `ClienteEntrante_Id`, `Liquidacion_Lote`, `Liquidacion_Tmh`, 
                          `Liquidacion_H2O`, `Liquidacion_Tms`, `Liquidacion_Leyau`, 
                          `Liquidacion_Leyag`, `Liquidacion_Inter`, `Liquidacion_Rec`,
                          `Liquidacion_Maquilla`, `Liquidacion_Factor`, `Liquidacion_Conscon`,
                          `Liquidacion_Escalador`, `Liquidacion_Stms`, `Liquidacion_Totalus`,
                          `Liquidacion_Reintegro`, `Liquidacion_Facturado`, `Personal_Id`
                          , `Liquidacion_Estado`,Empresa_Id) VALUES(proce,fecha,clienteEn,lote,tmh,h2o,tms,leyau,leyag,inter,rec,maquilla,factor,conscn,escalador,stms,totalus,
                                                                   reintegro,facturado,personal,estado,empresa)$$

DROP PROCEDURE IF EXISTS `Liquidacion_buscarLiquidacionPorLote_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Liquidacion_buscarLiquidacionPorLote_sp` (IN `lote` INT)  NO SQL
SELECT li.Liquidacion_Id,
li.Procedencia_Id,
li.Liquidacion_Fecha,
li.ClienteEntrante_Id,
li.Liquidacion_Lote,
li.Liquidacion_Tmh,
li.Liquidacion_H2O,
li.Liquidacion_Tms,
li.Liquidacion_Leyau,
li.Liquidacion_Leyag,
li.Liquidacion_Inter,
li.Liquidacion_Rec,
li.Liquidacion_Maquilla,
li.Liquidacion_Factor,
li.Liquidacion_Conscon,
li.Liquidacion_Escalador,
li.Liquidacion_Stms,
li.Liquidacion_Totalus,
li.Liquidacion_Reintegro,
li.Liquidacion_Facturado,
li.Liquidacion_Estado,
li.Personal_Id

FROM liquidacion as li INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=li.ClienteEntrante_Id WHERE LI.Liquidacion_Lote=lote$$

DROP PROCEDURE IF EXISTS `Liquidacion_BuscarParaDetalleValorizacion_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_BuscarParaDetalleValorizacion_sp` (IN `cliente` INT, IN `fecha` DATE)  NO SQL
SELECT li.Liquidacion_Id,
DATE_FORMAT(li.Liquidacion_Fecha,'%d-%m-%Y') as fecha,
pr.Procedencia_Descripcion,
li.Procedencia_Id,
li.Liquidacion_Lote,
li.Liquidacion_Tmh,
li.Liquidacion_H2O,
li.Liquidacion_Tms,
li.Liquidacion_Leyau,
li.Liquidacion_Leyag,
li.Liquidacion_Inter,
li.Liquidacion_Rec,
li.Liquidacion_Maquilla,
li.Liquidacion_Factor,
li.Liquidacion_Conscon,
li.Liquidacion_Escalador,
li.Liquidacion_Stms,
li.Liquidacion_Totalus,
li.Liquidacion_Estado
from liquidacion as li INNER join cliente_entrante as ce on ce.ClienteEntrante_Id=li.ClienteEntrante_Id INNER join procedencia as pr on li.Procedencia_Id=pr.Procedencia_Id WHERE ce.ClienteEntrante_Id=cliente and DATE_FORMAT(li.Liquidacion_Fecha,'%d-%m-%Y')=DATE_FORMAT(fecha,'%d-%m-%Y')$$

DROP PROCEDURE IF EXISTS `Liquidacion_BuscarPorClienteLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_BuscarPorClienteLike_sp` (IN `texto` VARCHAR(50))  NO SQL
SELECT 
ce.ClienteEntrante_Id,
ce.ClienteEntrante_DNI,
ce.ClienteEntrante_Nombres,
ce.ClienteEntrante_Apellidos,
tc.TipoCliente_Descripcion,
ce.ClienteEntrante_DNI,
DATE_FORMAT(li.Liquidacion_Fecha,'%Y-%m-%d') as fecha
from liquidacion as li INNER JOIN 
cliente_entrante as ce on ce.ClienteEntrante_Id=li.ClienteEntrante_Id
INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=ce.TipoCliente_Id
WHERE  li.Estado_Id=1 and concat(ce.ClienteEntrante_DNI,concat( ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres)) like concat('%',texto,'%') GROUP by DATE_FORMAT(li.Liquidacion_Fecha,'%Y-%m-%d'),ce.ClienteEntrante_Id,ce.ClienteEntrante_Nombres,ce.ClienteEntrante_Apellidos  order by li.Liquidacion_Fecha desc$$

DROP PROCEDURE IF EXISTS `Liquidacion_BuscarPorCodigoCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_BuscarPorCodigoCliente_sp` (IN `codigo` INT)  NO SQL
SELECT li.Liquidacion_Id,
li.Procedencia_Id,
li.Liquidacion_Fecha,
li.ClienteEntrante_Id,
li.Liquidacion_Lote,
li.Liquidacion_Tmh,
li.Liquidacion_H2O,
li.Liquidacion_Tms,
li.Liquidacion_Leyau,
li.Liquidacion_Leyag,
li.Liquidacion_Inter,
li.Liquidacion_Rec,
li.Liquidacion_Maquilla,
li.Liquidacion_Factor,
li.Liquidacion_Conscon,
li.Liquidacion_Escalador,
li.Liquidacion_Stms,
li.Liquidacion_Totalus,
li.Liquidacion_Reintegro,
li.Liquidacion_Facturado,
li.Liquidacion_Estado,
li.Personal_Id

FROM liquidacion as li INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=li.ClienteEntrante_Id WHERE ce.ClienteEntrante_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT li.Liquidacion_Id,
li.Procedencia_Id,
li.Liquidacion_Fecha,
li.ClienteEntrante_Id,
li.Liquidacion_Lote,
li.Liquidacion_Tmh,
li.Liquidacion_H2O,
li.Liquidacion_Tms,
li.Liquidacion_Leyau,
li.Liquidacion_Leyag,
li.Liquidacion_Inter,
li.Liquidacion_Rec,
li.Liquidacion_Maquilla,
li.Liquidacion_Factor,
li.Liquidacion_Conscon,
li.Liquidacion_Escalador,
li.Liquidacion_Stms,
li.Liquidacion_Totalus,
li.Liquidacion_Reintegro,
li.Liquidacion_Facturado,
li.Liquidacion_Estado,
li.Personal_Id

FROM liquidacion as li WHERE li.Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_BuscarPorDniClienteEntrante_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_BuscarPorDniClienteEntrante_sp` (IN `estados` INT, IN `cliente` INT)  NO SQL
SELECT 

ce.ClienteEntrante_Id,
ce.ClienteEntrante_DNI,
ce.ClienteEntrante_Nombres,
tc.TipoCliente_Descripcion,
DATE_FORMAT(li.Liquidacion_Fecha,'%d-%m-%Y') as fecha
from liquidacion as li INNER JOIN 
cliente_entrante as ce on ce.ClienteEntrante_Id=li.ClienteEntrante_Id
INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=ce.TipoCliente_Id
WHERE ce.ClienteEntrante_Id=cliente and li.Estado_Id=estados order by li.Liquidacion_Fecha desc$$

DROP PROCEDURE IF EXISTS `Liquidacion_BuscarPorTipoCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_BuscarPorTipoCliente_sp` (IN `estados` INT, IN `tipoCliente` INT)  NO SQL
SELECT

ce.ClienteEntrante_Id,
ce.ClienteEntrante_DNI,
ce.ClienteEntrante_Apellidos,
ce.ClienteEntrante_Nombres,
tc.TipoCliente_Descripcion,
DATE_FORMAT(li.Liquidacion_Fecha,'%Y-%m-%d') as fecha
from liquidacion as li INNER JOIN 
cliente_entrante as ce on ce.ClienteEntrante_Id=li.ClienteEntrante_Id
INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=ce.TipoCliente_Id
WHERE tc.TipoCliente_Id=tipoCliente and li.Estado_Id=estados
GROUP by ce.ClienteEntrante_Id,
ce.ClienteEntrante_DNI,
ce.ClienteEntrante_Nombres,
tc.TipoCliente_Descripcion,
concat(li.Liquidacion_Fecha,'%Y-%m-%d')
order by li.Liquidacion_Fecha desc$$

DROP PROCEDURE IF EXISTS `Liquidacion_CambiarEstadoReintegro_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Liquidacion_CambiarEstadoReintegro_sp` (IN `codigo` INT, IN `texto` CHAR(50))  NO SQL
UPDATE liquidacion as l set l.Liquidacion_Reintegro=texto WHERE l.Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from liquidacion WHERE Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_LlenarNuevo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_LlenarNuevo_sp` ()  NO SQL
SELECT li.Liquidacion_Fecha as fecha,li.Liquidacion_H2O,
li.Liquidacion_Leyag,
li.Liquidacion_Inter,
li.Liquidacion_Maquilla,
li.Liquidacion_Conscon
from liquidacion as li ORDER by li.Liquidacion_Id DESC LIMIT 1$$

DROP PROCEDURE IF EXISTS `Liquidacion_ModificarEstado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_ModificarEstado_sp` (IN `codigo` INT, IN `estado` VARCHAR(20))  NO SQL
UPDATE liquidacion as li set li.Liquidacion_Estado=estado WHERE li.Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_ModificarFacturado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_ModificarFacturado_sp` (IN `numeroF` TEXT, IN `codigo` INT)  NO SQL
UPDATE liquidacion as li set li.Liquidacion_Facturado=numeroF where li.Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_Modificar_sp` (IN `fecha` DATE, IN `clienteEn` INT, IN `proce` INT, IN `lote` TEXT, IN `tmh` TEXT, IN `h2o` TEXT, IN `tms` TEXT, IN `leyau` TEXT, IN `leyag` TEXT, IN `inter` TEXT, IN `rec` TEXT, IN `maquilla` TEXT, IN `factor` TEXT, IN `conscn` TEXT, IN `escalador` TEXT, IN `stms` TEXT, IN `estado` TEXT, IN `reintegro` TEXT, IN `facturado` TEXT, IN `totalus` TEXT, IN `personall` INT, IN `codigo` INT)  NO SQL
UPDATE liquidacion as li SET li.Procedencia_Id=proce,li.Liquidacion_Fecha=fecha,li.ClienteEntrante_Id=clienteEn,
li.Liquidacion_Lote=lote,li.Liquidacion_Tmh=tmh,li.Liquidacion_H2O=h2o,
li.Liquidacion_Tms=tms,li.Liquidacion_Leyau=leyau,li.Liquidacion_Leyag=leyag,
li.Liquidacion_Inter=inter,li.Liquidacion_Rec=rec,li.Liquidacion_Maquilla=maquilla,
li.Liquidacion_Factor=factor,li.Liquidacion_Conscon=conscn,li.Liquidacion_Escalador=escalador,
li.Liquidacion_Stms=stms,li.Liquidacion_Totalus=totalus,li.Liquidacion_Reintegro=reintegro,
li.Liquidacion_Facturado=facturado,li.Personal_Id=personall,li.Liquidacion_Estado=estado
WHERE li.Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `Liquidacion_MostrarGrafico_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_MostrarGrafico_sp` (IN `estado` VARCHAR(50))  NO SQL
SELECT  
l.Liquidacion_Id,
l.Liquidacion_Lote,
sum(l.Liquidacion_Totalus) totalus,
l.Liquidacion_Maquilla,
concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres) as tipoCliente
FROM liquidacion AS l INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=l.ClienteEntrante_Id
WHERE l.Estado_Id=estado
GROUP by l.ClienteEntrante_Id order by count(l.Liquidacion_Lote) desc$$

DROP PROCEDURE IF EXISTS `Liquidacion_MostrarLotesCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_MostrarLotesCliente_sp` ()  NO SQL
select 
li.Liquidacion_Id,
clie.ClienteEntrante_DNI,
concat(clie.ClienteEntrante_Nombres,' ',clie.ClienteEntrante_Apellidos) as nombrecompleto,
count(li.Liquidacion_Lote) as totallotes
from liquidacion as li inner join cliente_entrante as clie on li.ClienteEntrante_Id=clie.ClienteEntrante_Id where li.Estado_Id=1
GROUP by li.ClienteEntrante_Id order by COUNT(li.Liquidacion_Lote) DESC$$

DROP PROCEDURE IF EXISTS `Liquidacion_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT  
l.Liquidacion_Id,
p.Procedencia_Descripcion,
l.Liquidacion_Fecha,
concat(ce.ClienteEntrante_Apellidos,' '+ce.ClienteEntrante_Nombres)as tipoCliente,
l.Liquidacion_Lote,
l.Liquidacion_Tmh,
l.Liquidacion_H2O,
l.Liquidacion_Tms,
l.Liquidacion_Leyau,
l.Liquidacion_Leyag,
l.Liquidacion_Inter,
l.Liquidacion_Rec,
l.Liquidacion_Maquilla,
l.Liquidacion_Factor,
l.Liquidacion_Conscon,
l.Liquidacion_Escalador,
l.Liquidacion_Stms,
l.Liquidacion_Totalus,
l.Liquidacion_Reintegro,
l.Liquidacion_Facturado,
l.Liquidacion_Estado
FROM liquidacion AS l
inner join procedencia as p on p.Procedencia_Id=l.Procedencia_Id 
INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=l.ClienteEntrante_Id WHERE l.Estado_Id=estado
and concat(ce.ClienteEntrante_DNI,ce.ClienteEntrante_Apellidos,l.Liquidacion_Lote) LIKE concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `Liquidacion_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT  
l.Liquidacion_Id,
p.Procedencia_Descripcion,
l.Liquidacion_Fecha,
concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres)as tipoCliente,
l.Liquidacion_Lote,
l.Liquidacion_Tmh,
l.Liquidacion_H2O,
l.Liquidacion_Tms,
l.Liquidacion_Leyau,
l.Liquidacion_Leyag,
l.Liquidacion_Inter,
l.Liquidacion_Rec,
l.Liquidacion_Maquilla,
l.Liquidacion_Factor,
l.Liquidacion_Conscon,
l.Liquidacion_Escalador,
l.Liquidacion_Stms,
l.Liquidacion_Totalus,
l.Liquidacion_Reintegro,
l.Liquidacion_Facturado,
l.Liquidacion_Estado
FROM liquidacion AS l
inner join procedencia as p on p.Procedencia_Id=l.Procedencia_Id 
INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=l.ClienteEntrante_Id WHERE l.Estado_Id=1$$

DROP PROCEDURE IF EXISTS `Liquidacion_VerificarNumeroLote_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Liquidacion_VerificarNumeroLote_sp` (IN `numero` INT(20))  NO SQL
SELECT COUNT(li.Liquidacion_Id) as contador from liquidacion as li WHERE li.Liquidacion_Lote=numero$$

DROP PROCEDURE IF EXISTS `Log_Insertar`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Log_Insertar` (IN `log` VARCHAR(1000))  NO SQL
INSERT INTO log(Log) VALUES(log)$$

DROP PROCEDURE IF EXISTS `NotaCreditoDetalle_Agregar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoDetalle_Agregar_sp` (IN `Descripcion` VARCHAR(500), IN `ValorVenta` DECIMAL(9,2), IN `NotaCredito_Id` INT, IN `Personal_Id` INT)  NO SQL
INSERT INTO `notacredito_detalle`( `NotaCreditoDetalle_Descripcion`, `NotaCreditoDetalle_ValorVenta`, `NotaCredito_Id`, `Personal_Id`)
VALUES (Descripcion,ValorVenta,NotaCredito_Id,Personal_Id)$$

DROP PROCEDURE IF EXISTS `NotaCreditoDetalle_BuscarPorCodigoNotaCreditoParaNCE_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoDetalle_BuscarPorCodigoNotaCreditoParaNCE_sp` (IN `codigo` INT)  NO SQL
BEGIN
 set @TipoPrecio='01';
 set @Impuesto='18';
 set @TipoImpueto='10';
 set @ImpuestoSelectivo='0';
 set @OtroImpuesto='0';
 set @Unidad='TNE';
  
SELECT fd.NotaCreditoDetalle_Id,
CASE WHEN fd.NotaCreditoDetalle_Cantidad="" THEN '1' ELSE fd.NotaCreditoDetalle_Cantidad END as cantidad,
 @Unidad as unidad,
 CASE WHEN fd.NotaCreditoDetalle_ValorVenta="" THEN 0 else fd.NotaCreditoDetalle_ValorVenta end as Suma,
 CASE WHEN fd.NotaCreditoDetalle_PrecioUnitario="" THEN '1' ELSE fd.NotaCreditoDetalle_PrecioUnitario end as PrecioUnitario,
@TipoPrecio as TipoPrecio,
@Impuesto as Impuesto,
@TipoImpueto as TipoImpueto,
@ImpuestoSelectivo as ImpuestoSelectivo,
@OtroImpuesto as   OtroImpuesto,
fd.NotaCreditoDetalle_Descripcion as Descripcion
FROM notacredito_detalle as fd
where fd.NotaCredito_Id=codigo; 
END$$

DROP PROCEDURE IF EXISTS `NotaCreditoDetalle_BuscarPorNotaCredito_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoDetalle_BuscarPorNotaCredito_sp` (IN `codigo` INT)  NO SQL
SELECT 
ncd.NotaCreditoDetalle_Id,
ncd.NotaCreditoDetalle_Descripcion,
ncd.NotaCreditoDetalle_ValorVenta
from  notacredito_detalle as ncd WHERE ncd.NotaCredito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCreditoDetalle_Eliminar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoDetalle_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from notacredito_detalle WHERE NotaCreditoDetalle_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCreditoDetalle_Modificar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoDetalle_Modificar_sp` (IN `descripcion` VARCHAR(500), IN `ValorVenta` DECIMAL(9,2), IN `NotaCredito_Id` INT, IN `codigo` INT)  NO SQL
UPDATE notacredito_detalle as ncd SET 
ncd.NotaCreditoDetalle_Descripcion=descripcion,
ncd.NotaCreditoDetalle_ValorVenta=ValorVenta,
ncd.NotaCredito_Id=NotaCredito_Id
 WHERE ncd.NotaCreditoDetalle_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCreditoElectronica_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoElectronica_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
fe.NotaCreditoElectronica_FechaGeneracionArchivo
from notacredito_electronica as fe WHERE fe.NotaCredito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCreditoElectronica_ModificarDeBaja_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoElectronica_ModificarDeBaja_sp` (IN `notacredito` INT, IN `estado` INT, IN `fechabaja` VARCHAR(100), IN `rutaxml` VARCHAR(1024))  NO SQL
UPDATE notacredito_electronica as fe set fe.NotaCreditoElectronica_RutaXMLBaja=rutaxml,
fe.NotaCreditoElectronica_FechaBaja=fechabaja,
fe.EstadoNotaCredito_Id=estado
WHERE fe.NotaCredito_Id=notacredito$$

DROP PROCEDURE IF EXISTS `NotaCreditoElectronica_ModificarEnvio_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoElectronica_ModificarEnvio_sp` (IN `nc_codigo` INT, IN `rutaxml` VARCHAR(1000), IN `fechag` VARCHAR(100), IN `estados` INT)  NO SQL
UPDATE notacredito_electronica as nc SET nc.NotaCreditoElectronica_RutaXML=rutaxml,
nc.NotaCreditoElectronica_FechaGeneracionArchivo=fechag,
nc.EstadoNotaCredito_Id=estados
WHERE nc.NotaCredito_Id=nc_codigo$$

DROP PROCEDURE IF EXISTS `NotaCreditoElectronica_MostrarFacturas_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoElectronica_MostrarFacturas_sp` (IN `estado` INT, IN `fechaInicio` VARCHAR(50), IN `fechaFin` VARCHAR(50))  NO SQL
SELECT 
nd.NotaCredito_id,
nd.NotaCredito_NumeroNotaCreadito,
nd.NotaCredito_Total,
nd.NotaCredito_FechaEmision
from notacredito_electronica as nde INNER JOIN notacredito as nd on nde.NotaCredito_Id=nd.NotaCredito_id WHERE nde.EstadoNotaCredito_Id=estado and   nd.NotaCredito_FechaEmision BETWEEN fechaInicio and fechaFin ORDER by nd.NotaCredito_FechaEmision$$

DROP PROCEDURE IF EXISTS `NotaCreditoElectronico_BuscarParaCorreo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCreditoElectronico_BuscarParaCorreo_sp` (IN `codigo` INT)  NO SQL
SELECT nce.NotaCreditoElectronica_Id,nce.NotaCreditoElectronica_RutaXML,
nce.NotaCreditoElectronica_RutaPDF,nc.ProveedorServicio_Id ,nc.NotaCredito_NumeroNotaCreadito from notacredito_electronica as nce

INNER JOIN notacredito as nc on nc.NotaCredito_id=nce.NotaCredito_Id
WHERE nc.NotaCredito_id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCredito_Agregar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_Agregar_sp` (IN `NotaCredito_NumeroNotaCreadito` CHAR(50), IN `NotaCredito_NumeroFactura` CHAR(50), IN `ProveedorServicio_Id` INT, IN `NotaCredito_Denominacion` VARCHAR(80), IN `NotaCredito_FechaEmision` DATE, IN `NotaCredito_FechaEmision_Comprobante` DATE, IN `NotaCredito_SubtTotal` DECIMAL(9,2), IN `NotaCredito_IGV` DECIMAL(9,2), IN `NotaCredito_Total` DECIMAL(9,2), IN `NotaCredito_Moneda` CHAR(5), IN `NotaCredito_Lectura` VARCHAR(500), IN `Personal_Id` INT, IN `codigo` INT, IN `tipoNotaC` INT, IN `descripcionMotivo` VARCHAR(250))  NO SQL
INSERT INTO `notacredito`( `NotaCredito_NumeroNotaCreadito`, `NotaCredito_NumeroFactura`, `ProveedorServicio_Id`, `NotaCredito_Denominacion`, `NotaCredito_FechaEmision`, `NotaCredito_FechaEmision_Comprobante`, `NotaCredito_SubtTotal`, `NotaCredito_IGV`, `NotaCredito_Total`, `NotaCredito_Moneda`, `NotaCredito_Lectura`, `Personal_Id`,`NotaCredito_id`,`TipoNotaCredito_Id`,`NotaCredito_DescripcionMotivo`) 
VALUES (NotaCredito_NumeroNotaCreadito, NotaCredito_NumeroFactura, ProveedorServicio_Id, NotaCredito_Denominacion, NotaCredito_FechaEmision, NotaCredito_FechaEmision_Comprobante, NotaCredito_SubtTotal, NotaCredito_IGV, NotaCredito_Total, NotaCredito_Moneda, NotaCredito_Lectura, Personal_Id,codigo,tipoNotaC,descripcionMotivo)$$

DROP PROCEDURE IF EXISTS `NotaCredito_BuscarPorCodigoParaNCE_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_BuscarPorCodigoParaNCE_sp` (IN `codigo` INT)  NO SQL
SELECT
nc.NotaCredito_id,
nc.NotaCredito_NumeroNotaCreadito,
nc.NotaCredito_NumeroFactura,
nc.ProveedorServicio_Id,
nc.NotaCredito_Denominacion,
nc.NotaCredito_FechaEmision,
nc.NotaCredito_FechaEmision_Comprobante,
nc.NotaCredito_SubtTotal,
nc.NotaCredito_IGV,
nc.NotaCredito_Total,
nc.NotaCredito_Moneda,
nc.NotaCredito_Lectura,
e.Empresa_Nro_Documento,
nc.NotaCredito_DescripcionMotivo,
nc.TipoNotaCredito_Id,
nc.NotaCredito_Fecha_Registrado,
nc.Empresa_Id

from notacredito as nc INNER JOIN empresa as e on e.Empresa_id=nc.Empresa_Id WHERE nc.NotaCredito_id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCredito_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT
nc.NotaCredito_id,
nc.NotaCredito_NumeroNotaCreadito,
nc.NotaCredito_NumeroFactura,
nc.ProveedorServicio_Id,
nc.NotaCredito_Denominacion,
nc.NotaCredito_FechaEmision,
nc.NotaCredito_FechaEmision_Comprobante,
nc.NotaCredito_Moneda,
nc.TipoNotaCredito_Id,
nc.NotaCredito_DescripcionMotivo,
nc.NotaCredito_Lectura,
nc.NotaCredito_Total,
nc.NotaCredito_IGV,
nc.NotaCredito_SubtTotal
from notacredito as nc WHERE nc.NotaCredito_id=codigo$$

DROP PROCEDURE IF EXISTS `NotaCredito_ModificarCorrelativo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_ModificarCorrelativo_sp` (IN `correlativo` INT)  NO SQL
UPDATE factura_numero as f set f.FacturaNumero_Correlativo=correlativo 
WHERE f.FacturaNumero_Id=5$$

DROP PROCEDURE IF EXISTS `NotaCredito_Modificar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_Modificar_sp` (IN `NotaCredito_NumeroNotaCreadito` CHAR(50), IN `NotaCredito_NumeroFactura` CHAR(50), IN `ProveedorServicio_Id` INT, IN `NotaCredito_Denominacion` VARCHAR(80), IN `NotaCredito_FechaEmision` DATE, IN `NotaCredito_FechaEmision_Comprobante` DATE, IN `NotaCredito_SubtTotal` DECIMAL(9,2), IN `NotaCredito_IGV` DECIMAL(9,2), IN `NotaCredito_Total` DECIMAL(9,2), IN `NotaCredito_Moneda` CHAR(5), IN `NotaCredito_Lectura` VARCHAR(500), IN `codigo` INT, IN `tipoNotaC` INT, IN `descripcionMotivo` VARCHAR(250))  NO SQL
UPDATE `notacredito` 
SET 
`NotaCredito_NumeroNotaCreadito`=NotaCredito_NumeroNotaCreadito,
`NotaCredito_NumeroFactura`=NotaCredito_NumeroFactura,
`ProveedorServicio_Id`=ProveedorServicio_Id,
`NotaCredito_Denominacion`=NotaCredito_Denominacion,
`NotaCredito_FechaEmision`=NotaCredito_FechaEmision,
`NotaCredito_FechaEmision_Comprobante`=NotaCredito_FechaEmision_Comprobante,
`NotaCredito_SubtTotal`=NotaCredito_SubtTotal,
`NotaCredito_IGV`=NotaCredito_IGV,
`NotaCredito_Total`=NotaCredito_Total,
`NotaCredito_Moneda`=NotaCredito_Moneda,
`NotaCredito_Lectura`=NotaCredito_Lectura,
`TipoNotaCredito_Id`=tipoNotaC,
`NotaCredito_DescripcionMotivo`=descripcionMotivo
WHERE `NotaCredito_id`=codigo$$

DROP PROCEDURE IF EXISTS `NotaCredito_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(80))  NO SQL
select 
c.NotaCredito_id,
c.NotaCredito_NumeroNotaCreadito,
c.NotaCredito_NumeroFactura,
pro.ProveedorServicio_Razon_Social,
c.NotaCredito_FechaEmision,
c.NotaCredito_FechaEmision_Comprobante,
c.NotaCredito_SubtTotal,
c.NotaCredito_IGV,
c.NotaCredito_Total
from notacredito as c inner join proveedor_servicio as pro on c.ProveedorServicio_Id=pro.ProveedorServicio_Id  where
c.Estado_Id=estado and concat(pro.ProveedorServicio_Razon_Social,pro.ProveedorServicio_Ruc) like concat('%',texto,'%') ORDER by c.NotaCredito_NumeroNotaCreadito DESC$$

DROP PROCEDURE IF EXISTS `NotaCredito_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_MostrarTodos_sp` (IN `estado` INT)  NO SQL
select 
c.NotaCredito_id,
c.NotaCredito_NumeroNotaCreadito,
c.NotaCredito_NumeroFactura,
pro.ProveedorServicio_Razon_Social,
c.NotaCredito_FechaEmision,
c.NotaCredito_FechaEmision_Comprobante,
c.NotaCredito_SubtTotal,
c.NotaCredito_IGV,
c.NotaCredito_Total
from notacredito as c inner join proveedor_servicio as pro on c.ProveedorServicio_Id=pro.ProveedorServicio_Id  where
c.Estado_Id=estado ORDER by c.NotaCredito_NumeroNotaCreadito DESC$$

DROP PROCEDURE IF EXISTS `NotaCredito_ObtenerCorrelativo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_ObtenerCorrelativo_sp` ()  NO SQL
SELECT  MAX(fn.FacturaNumero_Correlativo) as correlativo from factura_numero as fn where fn.FacturaNumero_Id=5$$

DROP PROCEDURE IF EXISTS `NotaCredito_ObtenerSerie_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_ObtenerSerie_sp` ()  NO SQL
SELECT fn.FacturaNumero_Serio as serie  from factura_numero as fn where fn.FacturaNumero_Id=5$$

DROP PROCEDURE IF EXISTS `NotaCredito_ObtenerUltimoCodigo`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaCredito_ObtenerUltimoCodigo` ()  NO SQL
select max(nt.NotaCredito_id) as ultimaNotaCredito from notacredito as nt limit 1$$

DROP PROCEDURE IF EXISTS `NotaDebitoDetalle_Agregar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoDetalle_Agregar_sp` (IN `NotaDebitoDetalle_Cantidad` DECIMAL(9,3), IN `NotaDebitoDetalle_Unidad` CHAR(50), IN `NotaDebitoDetalle_Descripcion` VARCHAR(250), IN `NotaDebitoDetalle_PrecioUnitario` DECIMAL(9,2), IN `NotaDebitoDetalle_ValorVenta` DECIMAL(9,2), IN `NotaDebito_Id` INT, IN `Reintegro_Id` INT)  NO SQL
INSERT INTO `notadebitodetalle`(`NotaDebitoDetalle_Cantidad`, `NotaDebitoDetalle_Unidad`, `NotaDebitoDetalle_Descripcion`, `NotaDebitoDetalle_PrecioUnitario`, `NotaDebitoDetalle_ValorVenta`, `NotaDebito_Id`, `Reintegro_Id`) VALUES (NotaDebitoDetalle_Cantidad,
NotaDebitoDetalle_Unidad,
NotaDebitoDetalle_Descripcion,
NotaDebitoDetalle_PrecioUnitario,
NotaDebitoDetalle_ValorVenta,

NotaDebito_Id,
Reintegro_Id
)$$

DROP PROCEDURE IF EXISTS `NotaDebitoDetalle_BuscarPorCodigoNotaCreditoParaNCE_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoDetalle_BuscarPorCodigoNotaCreditoParaNCE_sp` (IN `codigo` INT)  NO SQL
BEGIN
 set @TipoPrecio='01';
 set @Impuesto='18';
 set @TipoImpueto='10';
 set @ImpuestoSelectivo='0';
 set @OtroImpuesto='0';
 set @Unidad='TNE';
  
SELECT fd.NotaDebitoDetalle_Id,
CASE WHEN fd.NotaDebitoDetalle_Cantidad="" THEN '1' ELSE fd.NotaDebitoDetalle_Cantidad END as cantidad,
 @Unidad as unidad,
 CASE WHEN fd.NotaDebitoDetalle_ValorVenta="" THEN 0 else fd.NotaDebitoDetalle_ValorVenta end as Suma,
 CASE WHEN fd.NotaDebitoDetalle_PrecioUnitario="" THEN '1' ELSE fd.NotaDebitoDetalle_PrecioUnitario end as PrecioUnitario,
@TipoPrecio as TipoPrecio,
@Impuesto as Impuesto,
@TipoImpueto as TipoImpueto,
@ImpuestoSelectivo as ImpuestoSelectivo,
@OtroImpuesto as   OtroImpuesto,
fd.NotaDebitoDetalle_Descripcion as Descripcion
FROM notadebitodetalle as fd
where fd.NotaDebito_Id=codigo; 
END$$

DROP PROCEDURE IF EXISTS `NotaDebitoDetalle_BuscarPornotaDebito_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoDetalle_BuscarPornotaDebito_sp` (IN `codigo` INT)  NO SQL
SELECT 
nd.NotaDebitoDetalle_Id,
nd.NotaDebitoDetalle_Cantidad,
nd.NotaDebitoDetalle_Unidad,
nd.NotaDebitoDetalle_Descripcion,
nd.NotaDebitoDetalle_PrecioUnitario,
nd.NotaDebitoDetalle_ValorVenta,
r.Reintegro_Lote
from notadebitodetalle as nd 
INNER JOIN reintegro as r on r.Reintegro_Id=nd.Reintegro_Id

WHERE nd.NotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebitoDetalle_Eliminar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoDetalle_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from notadebitodetalle WHERE NotaDebitoDetalle_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebitoDetalle_Modificar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoDetalle_Modificar_sp` (IN `NotaDebitoDetalle_Cantidad` DECIMAL(9,3), IN `NotaDebitoDetalle_Unidad` CHAR(50), IN `NotaDebitoDetalle_Descripcion` VARCHAR(250), IN `NotaDebitoDetalle_PrecioUnitario` DECIMAL(9,2), IN `NotaDebitoDetalle_ValorVenta` DECIMAL(9,2), IN `NotaDebito_Id` INT, IN `codigo` INT)  NO SQL
UPDATE `notadebitodetalle` SET

`NotaDebitoDetalle_Cantidad`=NotaDebitoDetalle_Cantidad,
`NotaDebitoDetalle_Unidad`=NotaDebitoDetalle_Unidad,
`NotaDebitoDetalle_Descripcion`=NotaDebitoDetalle_Descripcion,
`NotaDebitoDetalle_PrecioUnitario`=NotaDebitoDetalle_PrecioUnitario,
`NotaDebitoDetalle_ValorVenta`=NotaDebitoDetalle_ValorVenta,
`NotaDebito_Id`=NotaDebito_Id
WHERE NotaDebitoDetalle_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebitoElectronica_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoElectronica_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
fe.NotaDebitoElectronica_FechaGeneracionArchivo
from notadebito_electronica as fe WHERE fe.NotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebitoElectronica_ModificarDeBaja_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoElectronica_ModificarDeBaja_sp` (IN `notadebito` INT, IN `estado` INT, IN `fechabaja` VARCHAR(100), IN `rutaxml` VARCHAR(1024))  NO SQL
UPDATE notadebito_electronica as fe set fe.NotaDebitoElectronica_RutaXMLBaja=rutaxml,
fe.NotaDebitoElectronica_FechaBaja=fechabaja,
fe.EstadoNotaDebito_Id=estado
WHERE fe.NotaDebito_Id=notadebito$$

DROP PROCEDURE IF EXISTS `NotaDebitoElectronica_ModificarEnvio_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoElectronica_ModificarEnvio_sp` (IN `nc_codigo` INT, IN `rutaxml` VARCHAR(1000), IN `fechag` VARCHAR(100), IN `estados` INT)  NO SQL
UPDATE notadebito_electronica as nc SET nc.NotaDebitoElectronica_RutaXML=rutaxml,
nc.NotaDebitoElectronica_FechaGeneracionArchivo=fechag,
nc.EstadoNotaDebito_Id=estados
WHERE nc.NotaDebito_Id=nc_codigo$$

DROP PROCEDURE IF EXISTS `NotaDebitoElectronica_MostrarFacturas_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoElectronica_MostrarFacturas_sp` (IN `estado` INT, IN `fechaInicio` VARCHAR(50), IN `fechaFin` VARCHAR(50))  NO SQL
SELECT 
nd.NotaDebito_Id,
nd.NotaDebito_NumeroNotaDebito,
nd.NotaDebito_Total,
nd.NotaDebito_FechaEmision
from notadebito_electronica as nde INNER JOIN nota_debito as nd on nde.NotaDebito_Id=nd.NotaDebito_Id WHERE nde.EstadoNotaDebito_Id=estado and   nd.NotaDebito_FechaEmision BETWEEN fechaInicio and fechaFin ORDER by nd.NotaDebito_FechaEmision DESC$$

DROP PROCEDURE IF EXISTS `NotaDebitoElectronico_BuscarParaCorreo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoElectronico_BuscarParaCorreo_sp` (IN `codigo` INT)  NO SQL
SELECT nce.NotaDebitoElectronica_Id,nce.NotaDebitoElectronica_RutaXML,
nce.NotaDebitoElectronica_RutaPDF,nc.ProveedorServicio_Id ,nc.NotaDebito_NumeroNotaDebito from notadebito_electronica as nce

INNER JOIN nota_debito as nc on nc.NotaDebito_Id=nce.NotaDebito_Id
WHERE nc.NotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebitoNumero_Modificar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoNumero_Modificar_sp` (IN `Empresa_ID` INT, IN `NumeroCorrelativo` INT)  NO SQL
UPDATE  notadebito_numero SET NotaDebitoNumero_Correlativo=NumeroCorrelativo where Empresa_Id=Empresa_ID$$

DROP PROCEDURE IF EXISTS `NotaDebitoNumero_ObtenerCorrelativo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoNumero_ObtenerCorrelativo_sp` (IN `Empresa_Id` INT)  NO SQL
SELECT  MAX(fn.NotaDebitoNumero_Correlativo) as correlativo from notadebito_numero as fn where fn.Empresa_Id=Empresa_Id$$

DROP PROCEDURE IF EXISTS `NotaDebitoNumero_ObtenerSerie_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebitoNumero_ObtenerSerie_sp` (IN `Empresa_Id` INT)  NO SQL
SELECT fn.NotaDebitoNumero_Serie as serie  from notadebito_numero as fn where fn.Empresa_Id=Empresa_Id$$

DROP PROCEDURE IF EXISTS `NotaDebito_Agregar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_Agregar_sp` (IN `nroNotaDebito` CHAR(50), IN `nroFactura` CHAR(50), IN `proveedor` INT, IN `fecha_emi` DATE, IN `fecha_com` DATE, IN `denominac` VARCHAR(80), IN `conse` VARCHAR(500), IN `codigounico` CHAR(250), IN `valorventa` DECIMAL(9,2), IN `igv` DECIMAL(9,2), IN `total` DECIMAL(9,2), IN `moneda` CHAR(5), IN `lectura` VARCHAR(500), IN `personal` INT, IN `codigo` INT, IN `descripcionMotivo` VARCHAR(250), IN `tipoNotadebito` INT)  NO SQL
INSERT INTO `nota_debito`(`NotaDebito_NumeroNotaDebito`, `NotaDebito_NumeroFactura`, `ProveedorServicio_Id`, `NotaDebito_Denominacion`, `NotaDebito_FechaEmision`, `NotaDebito_FechaEmision_Comprobante`, `NotaDebito_Consecion`, `NotaDebito_CodigoUnico`, `NotaDebito_ValorVenta`, `NotaDebito_IGV`, `NotaDebito_Total`, `NotaDebito_Moneda`, `NotaDebito_Lectura`,  `Personal_Id`,NotaDebito_Id,NotaDebito_DescripcionMotivo,TipoNotaDebito_Id)
VALUES (nroNotaDebito,nroFactura,proveedor,denominac,fecha_emi,fecha_com,conse,codigounico,valorventa,igv,total,moneda,lectura,personal,codigo,descripcionMotivo,tipoNotadebito)$$

DROP PROCEDURE IF EXISTS `NotaDebito_BuscarPorCodigoParaNCE_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_BuscarPorCodigoParaNCE_sp` (IN `codigo` INT)  NO SQL
SELECT
nc.NotaDebito_Id,
nc.NotaDebito_NumeroNotaDebito,
nc.NotaDebito_NumeroFactura,
nc.ProveedorServicio_Id,
nc.NotaDebito_Denominacion,
nc.NotaDebito_FechaEmision,
nc.NotaDebito_FechaEmision_Comprobante,
nc.NotaDebito_ValorVenta,
nc.NotaDebito_IGV,
nc.NotaDebito_Total,
nc.NotaDebito_Moneda,
nc.NotaDebito_Lectura,
e.Empresa_Nro_Documento,
nc.NotaDebito_DescripcionMotivo,
nc.TipoNotaDebito_Id,
nc.Empresa_Id,
NC.NotaDebito_Fecha_Registrado

from nota_debito as nc INNER JOIN empresa as e on e.Empresa_id=nc.Empresa_Id WHERE nc.NotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebito_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
nd.NotaDebito_Id,
nd.NotaDebito_NumeroNotaDebito,
nd.NotaDebito_NumeroFactura,
nd.ProveedorServicio_Id,
nd.NotaDebito_Denominacion,
nd.NotaDebito_FechaEmision,
nd.NotaDebito_FechaEmision_Comprobante,
nd.NotaDebito_Consecion,
nd.NotaDebito_CodigoUnico,
nd.NotaDebito_Moneda,
nd.NotaDebito_CodigoUnico,
nd.NotaDebito_ValorVenta,
nd.NotaDebito_IGV,
nd.NotaDebito_Total,
nd.NotaDebito_Lectura,
nd.TipoNotaDebito_Id,
nd.NotaDebito_DescripcionMotivo
from nota_debito as nd WHERE nd.NotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebito_Modificar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_Modificar_sp` (IN `NotaDebito_NumeroNotaDebito` CHAR(50), IN `NotaDebito_NumeroFactura` CHAR(50), IN `ProveedorServicio_Id` INT, IN `NotaDebito_Denominacion` VARCHAR(80), IN `NotaDebito_FechaEmision` DATE, IN `NotaDebito_FechaEmision_Comprobante` DATE, IN `NotaDebito_Consecion` VARCHAR(80), IN `NotaDebito_CodigoUnico` VARCHAR(500), IN `NotaDebito_ValorVenta` DECIMAL(9,2), IN `NotaDebito_IGV` DECIMAL(9,2), IN `NotaDebito_Total` DECIMAL(9,2), IN `NotaDebito_Moneda` CHAR(5), IN `NotaDebito_Lectura` VARCHAR(500), IN `codigo` INT, IN `motibodebito` VARCHAR(250), IN `tiponotadebito_id` INT)  NO SQL
UPDATE `nota_debito` SET 
`NotaDebito_NumeroNotaDebito`=NotaDebito_NumeroNotaDebito,
`NotaDebito_NumeroFactura`=NotaDebito_NumeroFactura,
`ProveedorServicio_Id`=ProveedorServicio_Id,
`NotaDebito_Denominacion`=NotaDebito_Denominacion,
`NotaDebito_FechaEmision`=NotaDebito_FechaEmision,
`NotaDebito_FechaEmision_Comprobante`=NotaDebito_FechaEmision_Comprobante,
`NotaDebito_Consecion`=NotaDebito_Consecion,
`NotaDebito_CodigoUnico`=NotaDebito_CodigoUnico,
`NotaDebito_ValorVenta`=NotaDebito_ValorVenta,
`NotaDebito_IGV`=NotaDebito_IGV,
`NotaDebito_Total`=NotaDebito_Total,
`NotaDebito_Moneda`=NotaDebito_Moneda,
`NotaDebito_Lectura`=NotaDebito_Lectura,
NotaDebito_DescripcionMotivo=motibodebito,
TipoNotaDebito_Id=tiponotadebito_id
WHERE NotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `NotaDebito_MostrarTodosPorlIke_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_MostrarTodosPorlIke_sp` (IN `texto` VARBINARY(80))  NO SQL
SELECT 
nd.NotaDebito_Id,
nd.NotaDebito_NumeroNotaDebito,
nd.NotaDebito_NumeroFactura,
ps.ProveedorServicio_Razon_Social,
nd.NotaDebito_FechaEmision,
nd.NotaDebito_FechaEmision_Comprobante,
nd.NotaDebito_ValorVenta,
nd.NotaDebito_IGV,
nd.NotaDebito_Total,
nd.NotaDebito_Moneda
from nota_debito as nd
INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=nd.ProveedorServicio_Id

WHERE nd.Estado_Id=1 and concat(ps.ProveedorServicio_Razon_Social,ps.ProveedorServicio_Ruc) like concat('%',texto,'%') ORDER by nd.NotaDebito_NumeroNotaDebito DESC$$

DROP PROCEDURE IF EXISTS `NotaDebito_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_MostrarTodos_sp` ()  NO SQL
SELECT 
nd.NotaDebito_Id,
nd.NotaDebito_NumeroNotaDebito,
nd.NotaDebito_NumeroFactura,
ps.ProveedorServicio_Razon_Social,
nd.NotaDebito_FechaEmision,
nd.NotaDebito_FechaEmision_Comprobante,
nd.NotaDebito_ValorVenta,
nd.NotaDebito_IGV,
nd.NotaDebito_Total,
nd.NotaDebito_Moneda
from nota_debito as nd
INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=nd.ProveedorServicio_Id

WHERE nd.Estado_Id=1 ORDER by nd.NotaDebito_NumeroNotaDebito DESC$$

DROP PROCEDURE IF EXISTS `NotaDebito_ObtenerUltimoCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `NotaDebito_ObtenerUltimoCodigo_sp` ()  NO SQL
select max(nt.NotaDebito_Id) as ultimaNotaDebito from nota_debito as nt limit 1$$

DROP PROCEDURE IF EXISTS `PagoPersonal_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoPersonal_Agregar_sp` (IN `monto` DECIMAL(9,2), IN `fecha` DATE, IN `contrato` INT, IN `empresa` INT)  NO SQL
INSERT INTO `pago_personal`(`Contrato_Id`, `PagoPersonal_Fecha`, `PagoPersonal_Monto`, `Empresa_Id`) VALUES (contrato,fecha,monto,empresa)$$

DROP PROCEDURE IF EXISTS `PagoPersonal_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoPersonal_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
pt.PagoPersonal_Id,
pt.Contrato_Id,
pt.PagoPersonal_Fecha,
pt.PagoPersonal_Monto

from pago_personal as pt WHERE pt.PagoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `PagoPersonal_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoPersonal_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from pago_personal WHERE PagoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `PagoPersonal_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoPersonal_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(250))  NO SQL
SELECT 
pp.PagoPersonal_Id,
pp.PagoPersonal_Fecha,
pp.PagoPersonal_Monto,
e.Estado_Descripcion,
concat(p.Personal_Apellidos, ' ',p.Personal_Nombres) as personal
from pago_personal as pp INNER JOIN contrato as c on c.Contrato_Id=pp.Contrato_Id INNER JOIN personal as p on p.Personal_Id=c.Personal_Id
INNER JOIN estado as e on e.Estado_Id=pp.Estado_Id
WHERE concat(p.Personal_Nombres,p.Personal_DNI,p.Personal_Apellidos) like concat('%',texto,'%') and pp.Estado_Id=estado

ORDER by pp.PagoPersonal_Fecha desc$$

DROP PROCEDURE IF EXISTS `PagoPersonal_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoPersonal_MostrarTodos_sp` ()  NO SQL
SELECT 
pp.PagoPersonal_Id,
pp.PagoPersonal_Fecha,
pp.PagoPersonal_Monto,
e.Estado_Descripcion,
concat(p.Personal_Apellidos, ' ',p.Personal_Nombres) as personal
from pago_personal as pp INNER JOIN contrato as c on c.Contrato_Id=pp.Contrato_Id INNER JOIN personal as p on p.Personal_Id=c.Personal_Id
INNER JOIN estado as e on e.Estado_Id=pp.Estado_Id

ORDER by pp.PagoPersonal_Fecha desc$$

DROP PROCEDURE IF EXISTS `PagoPersonal_ObtenerUltimoCodigoInsertado_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `PagoPersonal_ObtenerUltimoCodigoInsertado_sp` ()  NO SQL
SELECT max(pp.PagoPersonal_Id) as codigo from pago_personal as pp LIMIT 1$$

DROP PROCEDURE IF EXISTS `PagoTransporte_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_Agregar_sp` (IN `proveedor` INT, IN `fecha` DATE, IN `nrofactura` CHAR(30), IN `peso` DECIMAL(9,2), IN `total` DECIMAL(9,2), IN `detraccion` DECIMAL(9,2), IN `descontar` VARCHAR(12), IN `adelantos` DECIMAL(9,2), IN `importe` DECIMAL(9,2), IN `nroOperacion` TEXT, IN `fechaPago` DATE, IN `tipoCliente` INT, IN `personals` INT, IN `precio` DECIMAL(9,2), IN `estado` VARCHAR(20), IN `rutaBaucher` TEXT, IN `empresas` INT)  NO SQL
INSERT INTO `pago_transporte`( `ProveedorServicio_Id`, `PagoTransporte_Fecha`, `PagoTransporte_NroFactura`, `PagoTransporte_Peso`, `PagoTransporte_Precio`, `PagoTransporte_Total`, `PagoTransporte_Detraccion`, `PagoTransporte_Descontar`, `PagoTransporte_Adelanto`, `PagoTransporte_Importe`, `PagoTransporte_NroOperacion`, `PagoTransporte_FechaPago`,  `TipoCliente_Id`,  `Personal_Id`,PagoTransporte_Estado,PagoTransporte_RutaBaucher,Empresa_Id) VALUES(proveedor,
fecha,nrofactura,peso,precio,total,detraccion,descontar,adelantos,importe,nroOperacion,fechaPago,tipoCliente,personals,estado,rutaBaucher,empresas
)$$

DROP PROCEDURE IF EXISTS `PagoTransporte_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT
pt.PagoTransporte_Id,
pt.PagoTransporte_Fecha,
pt.PagoTransporte_NroFactura,
pt.PagoTransporte_Peso,
pt.PagoTransporte_Precio,
pt.PagoTransporte_Total,
pt.PagoTransporte_Detraccion,
pt.PagoTransporte_Descontar,
pt.PagoTransporte_Adelanto,
pt.PagoTransporte_Importe,
pt.PagoTransporte_NroOperacion,
pt.PagoTransporte_FechaPago,
pt.ProveedorServicio_Id,
pt.PagoTransporte_Estado,
pt.PagoTransporte_RutaBaucher,
pt.TipoCliente_Id
from pago_transporte as pt  WHERE pt.PagoTransporte_Id=codigo$$

DROP PROCEDURE IF EXISTS `PagoTransporte_CalcularTotalNoPagado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_CalcularTotalNoPagado_sp` (IN `estados` VARCHAR(30))  NO SQL
SELECT sum(pt.PagoTransporte_Importe) as totalPagado from pago_transporte as pt WHERE pt.Estado_Id=1 and pt.PagoTransporte_Estado=estados$$

DROP PROCEDURE IF EXISTS `PagoTransporte_CalcularTotalPagado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_CalcularTotalPagado_sp` (IN `estados` VARCHAR(30))  NO SQL
SELECT sum(pt.PagoTransporte_Importe) as totalPagado from pago_transporte as pt WHERE pt.Estado_Id=1 and pt.PagoTransporte_Estado=estados$$

DROP PROCEDURE IF EXISTS `PagoTransporte_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from pago_transporte  WHERE PagoTransporte_Id=codigo$$

DROP PROCEDURE IF EXISTS `PagoTransporte_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_Modificar_sp` (IN `proveedor` INT, IN `fecha` DATE, IN `nroFactura` CHAR(30), IN `peso` DECIMAL(9,2), IN `total` DECIMAL(9,2), IN `detraccion` DECIMAL(9,2), IN `descontar` VARCHAR(30), IN `adelantos` DECIMAL(9,2), IN `importe` DECIMAL(9,2), IN `nroOperacion` TEXT, IN `fechaPago` DATE, IN `tipoCliente` INT, IN `personals` INT, IN `precio` DECIMAL(9,2), IN `codigo` INT, IN `estado` VARCHAR(20), IN `rutaBaucher` TEXT)  NO SQL
UPDATE pago_transporte as pt SET pt.ProveedorServicio_Id=proveedor,
pt.PagoTransporte_Fecha=fecha,
pt.PagoTransporte_NroFactura=nroFactura,
pt.PagoTransporte_Peso=peso,
pt.PagoTransporte_Precio=precio,
pt.PagoTransporte_Total=total,
pt.PagoTransporte_Detraccion=detraccion,
pt.PagoTransporte_Descontar=descontar,
pt.PagoTransporte_Adelanto=adelantos,
pt.PagoTransporte_Importe=importe,
pt.PagoTransporte_NroOperacion=nroOperacion,
pt.PagoTransporte_FechaPago=fechaPago,
pt.TipoCliente_Id=tipoCliente,
pt.Personal_Id=personals,
pt.PagoTransporte_RutaBaucher=rutaBaucher,
pt.PagoTransporte_Estado=estado
WHERE pt.PagoTransporte_Id=codigo$$

DROP PROCEDURE IF EXISTS `PagoTransporte_MostrarPorNumeroFactura_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_MostrarPorNumeroFactura_sp` (IN `estados` INT, IN `factura` CHAR(30))  NO SQL
SELECT 
pt.PagoTransporte_Id,
ps.ProveedorServicio_Razon_Social,
pt.PagoTransporte_Fecha,
pt.PagoTransporte_NroFactura,
pt.PagoTransporte_Peso,
pt.PagoTransporte_Precio,
pt.PagoTransporte_Total,
pt.PagoTransporte_Detraccion,
pt.PagoTransporte_Descontar,
pt.PagoTransporte_Adelanto,
pt.PagoTransporte_Importe,
pt.PagoTransporte_NroOperacion,
pt.PagoTransporte_FechaPago,
tc.TipoCliente_Descripcion
from pago_transporte as pt INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=pt.ProveedorServicio_Id INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=pt.TipoCliente_Id WHERE pt.Estado_Id=estados
and pt.PagoTransporte_NroFactura=factura$$

DROP PROCEDURE IF EXISTS `PagoTransporte_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT 
pt.PagoTransporte_Id,
ps.ProveedorServicio_Razon_Social,
pt.PagoTransporte_Fecha,
pt.PagoTransporte_NroFactura,
pt.PagoTransporte_Peso,
pt.PagoTransporte_Precio,
pt.PagoTransporte_Total,
pt.PagoTransporte_Detraccion,
pt.PagoTransporte_Descontar,
pt.PagoTransporte_Adelanto,
pt.PagoTransporte_Importe,
pt.PagoTransporte_NroOperacion,
pt.PagoTransporte_FechaPago,
tc.TipoCliente_Descripcion
from pago_transporte as pt INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=pt.ProveedorServicio_Id INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=pt.TipoCliente_Id WHERE pt.Estado_Id=estado and concat(ps.ProveedorServicio_Razon_Social,pt.PagoTransporte_NroFactura) like concat('%',texto,'%')
ORDER by pt.PagoTransporte_Id DESC$$

DROP PROCEDURE IF EXISTS `PagoTransporte_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagoTransporte_MostrarTodos_sp` (IN `estados` INT)  NO SQL
SELECT 
pt.PagoTransporte_Id,
ps.ProveedorServicio_Razon_Social,
pt.PagoTransporte_Fecha,
pt.PagoTransporte_NroFactura,
pt.PagoTransporte_Peso,
pt.PagoTransporte_Precio,
pt.PagoTransporte_Total,
pt.PagoTransporte_Detraccion,
pt.PagoTransporte_Descontar,
pt.PagoTransporte_Adelanto,
pt.PagoTransporte_Importe,
pt.PagoTransporte_NroOperacion,
pt.PagoTransporte_FechaPago,
tc.TipoCliente_Descripcion
from pago_transporte as pt INNER JOIN proveedor_servicio as ps on ps.ProveedorServicio_Id=pt.ProveedorServicio_Id INNER JOIN tipo_cliente as tc on tc.TipoCliente_Id=pt.TipoCliente_Id WHERE pt.Estado_Id=estados
ORDER by pt.PagoTransporte_Id DESC$$

DROP PROCEDURE IF EXISTS `Pago_VerificarSiEstaPagadoContrato_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Pago_VerificarSiEstaPagadoContrato_sp` (IN `fecha` DATE, IN `codigoContrato` INT)  NO SQL
SELECT count(pp.PagoPersonal_Id) as suma  from pago_personal as pp WHERE DATE_FORMAT(pp.PagoPersonal_Fecha,'%d/%m/%Y')=DATE_FORMAT(fecha,'%d/%m/%Y')
and pp.Contrato_Id=codigoContrato$$

DROP PROCEDURE IF EXISTS `Permisos_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Permisos_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT p.Permisos_Id,
p.Permisos_Descripcion
from permisos as p WHERE p.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `Personal_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_Agregar_sp` (IN `nombres` VARCHAR(64), IN `apellidos` VARCHAR(100), IN `dni` CHAR(8), IN `sexo` CHAR(1), IN `telefono` CHAR(15), IN `direccion` VARCHAR(200), IN `fechaNa` DATE, IN `tipoPersonal` INT, IN `email` VARCHAR(150), IN `contra` VARCHAR(200), IN `empresa` INT)  NO SQL
INSERT INTO personal(
    Personal_Nombres, 
    Personal_Apellidos,    
    Personal_DNI,
    Personal_Sexo,
    Personal_Telefono,
    Personal_Email,
    Personal_Direccion,
    Personal_Fecha_Nacimiento,    
    TipoPersonal_Id,
Personal_Password,
    Empresa_Id
) VALUES
    (nombres,apellidos,dni,sexo,telefono,email,direccion,fechaNa,tipoPersonal,contra,empresa)$$

DROP PROCEDURE IF EXISTS `Personal_buscarPorCodigo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_buscarPorCodigo` (IN `codigo` INT)  NO SQL
SELECT
p.Personal_Id,
p.Personal_Nombres,
p.Personal_Apellidos,
p.Personal_DNI ,
p.Personal_Sexo ,
p.Personal_Telefono ,
p.Personal_Email ,
p.Personal_Direccion ,
p.Personal_Sueldo,
p.Personal_Fecha_Pago,
p.Personal_Fecha_Nacimiento,
p.Personal_Fecha_Ingreso,
p.Personal_TotalDiasPago,
tp.TipoPersonal_Id,
p.Personal_Password,
e.Estado_Id
from personal as p inner join tipo_personal as tp on tp.TipoPersonal_Id=p.TipoPersonal_Id INNER join estado as e on e.Estado_Id=p.Estado_Id where p.Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `Personal_BuscarPorDni_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_BuscarPorDni_sp` (IN `dni` INT)  NO SQL
SELECT
p.Personal_Id ,
p.Personal_Nombres ,
p.Personal_Apellidos ,
p.Personal_DNI ,
p.Personal_Sexo ,
p.Personal_Telefono ,
p.Personal_Email ,
p.Personal_Direccion ,
tp.TipoPersonal_Descripcion 
from personal as p inner join tipo_personal as tp on tp.TipoPersonal_Id=p.TipoPersonal_Id where p.Estado_Id='1' and p.Personal_DNI like dni$$

DROP PROCEDURE IF EXISTS `Personal_buscarPorNombre_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_buscarPorNombre_sp` (IN `nombre` VARCHAR(50))  NO SQL
SELECT
p.Personal_Id ,
p.Personal_Nombres ,
p.Personal_Apellidos ,
p.Personal_DNI ,
p.Personal_Sexo ,
p.Personal_Telefono ,
p.Personal_Email ,
p.Personal_Direccion ,
tp.TipoPersonal_Descripcion 
from personal as p inner join tipo_personal as tp on tp.TipoPersonal_Id=p.TipoPersonal_Id where p.Personal_Nombres like '$nombre$' and p.Estado_Id='1'$$

DROP PROCEDURE IF EXISTS `Personal_BuscarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_BuscarTodosPorLike_sp` (IN `dni` VARCHAR(50))  NO SQL
SELECT
p.Personal_Id ,
p.Personal_Nombres ,
p.Personal_Apellidos ,
p.Personal_DNI ,
p.Personal_Sexo ,
p.Personal_Telefono ,
p.Personal_Email ,
p.Personal_Direccion ,
tp.TipoPersonal_Descripcion 
from personal as p inner join tipo_personal as tp on tp.TipoPersonal_Id=p.TipoPersonal_Id where p.Estado_Id=1 and concat( p.Personal_DNI,p.Personal_Nombres,p.Personal_Apellidos) like concat('%',dni,'%')$$

DROP PROCEDURE IF EXISTS `Personal_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_Eliminar_sp` (IN `codigo` INT)  NO SQL
UPDATE personal as p SET p.Estado_Id=3 where p.Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `Personal_ModificarEstadoNotificacion_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_ModificarEstadoNotificacion_sp` (IN `codigo` INT, IN `estadoNot` VARCHAR(50))  NO SQL
UPDATE personal as p SET p.Personalk_EstadoNotificacion=estadoNot WHERE p.Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `Personal_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_Modificar_sp` (IN `nombres` VARCHAR(64), IN `apellidos` VARCHAR(124), IN `dni` CHAR(8), IN `sexo` CHAR(1), IN `telefono` CHAR(15), IN `direccion` VARCHAR(200), IN `fechaNa` DATE, IN `email` VARCHAR(150), IN `contra` VARCHAR(150), IN `codigo` INT, IN `tipoPersonal` INT)  NO SQL
UPDATE personal set Personal_Nombres=nombres, Personal_Apellidos=apellidos, Personal_DNI=dni, Personal_Sexo=sexo, Personal_Telefono=telefono, Personal_Direccion=direccion, Personal_Fecha_Nacimiento=fechaNa, TipoPersonal_Id=tipoPersonal, Personal_Email=email, Personal_Password=contra WHERE Personal_Id=codigo$$

DROP PROCEDURE IF EXISTS `Personal_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT
p.Personal_Id ,
p.Personal_Nombres ,
p.Personal_Apellidos ,
p.Personal_DNI ,
p.Personal_Sexo ,
p.Personal_Telefono ,
p.Personal_Email ,
p.Personal_Direccion ,
tp.TipoPersonal_Descripcion 
from personal as p inner join tipo_personal as tp on tp.TipoPersonal_Id=p.TipoPersonal_Id where p.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `Personal_ObtenerPersonalLista_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_ObtenerPersonalLista_sp` ()  NO SQL
SELECT
p.Personal_Id,
p.Personal_Nombres,
p.Personal_Apellidos,
p.Personal_DNI ,
p.Personal_Fecha_Nacimiento,
p.Personalk_EstadoNotificacion


from personal as p where p.Estado_Id=1$$

DROP PROCEDURE IF EXISTS `Personal_VerificarInicioSesion_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Personal_VerificarInicioSesion_sp` (IN `dni` CHAR(8), IN `contrasena` VARCHAR(80), IN `empresa` INT)  NO SQL
SELECT p.Personal_Id,
p.Personal_Nombres,
p.Personal_Apellidos,
p.Personal_DNI,
p.Personal_Telefono,
p.TipoPersonal_Id,
p.Personal_Sueldo,
p.Personal_Email,
p.Empresa_Id,
p.TipoPersonal_Id
from personal as p WHERE p.Personal_DNI=dni and p.Personal_Password=contrasena and p.Estado_Id=1 and p.Empresa_Id=empresa$$

DROP PROCEDURE IF EXISTS `Procedencia_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedencia_Agregar_sp` (IN `descripcion` VARCHAR(80), IN `personal` INT)  NO SQL
INSERT INTO procedencia(Procedencia_Descripcion,Personal_Id) VALUES(descripcion,personal)$$

DROP PROCEDURE IF EXISTS `Procedencia_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedencia_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT p.Procedencia_Id,p.Procedencia_Descripcion from procedencia as p WHERE p.Procedencia_Id=codigo$$

DROP PROCEDURE IF EXISTS `Procedencia_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedencia_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from procedencia WHERE Procedencia_Id=codigo$$

DROP PROCEDURE IF EXISTS `Procedencia_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedencia_Modificar_sp` (IN `descripcion` VARCHAR(80), IN `personal` INT, IN `codigo` INT)  NO SQL
UPDATE procedencia SET Procedencia_Descripcion=descripcion, Personal_Id=personal WHERE Procedencia_Id=codigo$$

DROP PROCEDURE IF EXISTS `Procedencia_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedencia_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT p.Procedencia_Id,p.Procedencia_Descripcion,concat(pr.Personal_Apellidos,pr.Personal_Nombres) AS Personal from procedencia as p INNER JOIN personal as pr on pr.Personal_Id=p.Personal_Id where p.Estado_Id=estado
and p.Procedencia_Descripcion like concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `Procedencia_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Procedencia_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT p.Procedencia_Id,p.Procedencia_Descripcion,concat(pr.Personal_Apellidos,pr.Personal_Nombres) AS Personal from procedencia as p INNER JOIN personal as pr on pr.Personal_Id=p.Personal_Id where p.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProveedorServicio_Agregar_sp` (IN `razonSocial` VARCHAR(80), IN `ruc` VARCHAR(50), IN `entidad` CHAR(150), IN `cuenta` CHAR(70), IN `telefono` CHAR(15), IN `direccion` VARCHAR(200), IN `email` VARCHAR(100), IN `tipoPro` INT, IN `personal` INT, IN `empresa` INT)  NO SQL
INSERT INTO proveedor_servicio(ProveedorServicio_Razon_Social, ProveedorServicio_Ruc, ProveedorServicio_Entidad, ProveedorServicio_Cuenta, ProveedorServicio_Telefono, ProveedorServicio_Direccion, ProveedorServicio_Email, TipoProveedor_Id,Personal_Id,Empresa_Id)
VALUES(razonSocial,ruc,entidad,cuenta,telefono,direccion,email,tipoPro,personal,empresa)$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProveedorServicio_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT ps.ProveedorServicio_Id,
ps.ProveedorServicio_Razon_Social,
ps.ProveedorServicio_Ruc,
ps.ProveedorServicio_Entidad,
ps.ProveedorServicio_Cuenta,
ps.ProveedorServicio_Telefono,
ps.ProveedorServicio_Direccion,
ps.ProveedorServicio_Email,
ps.TipoProveedor_Id,
ps.ProveedorServicio_TipoDocumento

from proveedor_servicio as ps WHERE ps.ProveedorServicio_Id=codigo$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProveedorServicio_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from proveedor_servicio WHERE ProveedorServicio_Id=codigo$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProveedorServicio_Modificar_sp` (IN `razonSocial` VARCHAR(80), IN `ruc` VARCHAR(50), IN `entidad` CHAR(150), IN `cuenta` CHAR(70), IN `telefono` CHAR(15), IN `direccion` VARCHAR(200), IN `tipoPro` INT, IN `personals` INT, IN `codigo` INT, IN `email` CHAR(100))  NO SQL
UPDATE proveedor_servicio as ps set ps.ProveedorServicio_Razon_Social=razonSocial,
ps.ProveedorServicio_Ruc=ruc,
ps.ProveedorServicio_Entidad=entidad,
ps.ProveedorServicio_Cuenta=cuenta,
ps.ProveedorServicio_Telefono=telefono,
ps.ProveedorServicio_Direccion=direccion,
ps.TipoProveedor_Id=tipoPro,
ps.Personal_Id=personals,
ps.ProveedorServicio_Email=email
WHERE ps.ProveedorServicio_Id=codigo$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_MostrarPorFacturacionTodosPorLike_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `ProveedorServicio_MostrarPorFacturacionTodosPorLike_sp` (IN `estado` INT, IN `buscar` VARCHAR(50))  NO SQL
SELECT ps.ProveedorServicio_Id,
ps.ProveedorServicio_Razon_Social,
ps.ProveedorServicio_Ruc,
ps.ProveedorServicio_Entidad,
ps.ProveedorServicio_Cuenta,
ps.ProveedorServicio_Telefono,
ps.ProveedorServicio_Direccion,
ps.ProveedorServicio_Email,
t.TipoProveedor_Descripcion,
CONCAT(p.Personal_Apellidos,' '+p.Personal_Nombres) as personals
from proveedor_servicio as ps 
INNER JOIN personal as p on p.Personal_Id=ps.Personal_Id
INNER JOIN tipo_proveedor as t on ps.TipoProveedor_Id=t.TipoProveedor_Id
WHERE t.TipoProveedor_Descripcion='POR FACTURACION' and ps.Estado_Id=estado and concat(ps.ProveedorServicio_Razon_Social,ps.ProveedorServicio_Ruc) like concat('%',buscar,'%')$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_MostrarPorFacturacion_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `ProveedorServicio_MostrarPorFacturacion_sp` (IN `estado` INT)  NO SQL
SELECT ps.ProveedorServicio_Id,
ps.ProveedorServicio_Razon_Social,
ps.ProveedorServicio_Ruc,
ps.ProveedorServicio_Entidad,
ps.ProveedorServicio_Cuenta,
ps.ProveedorServicio_Telefono,
ps.ProveedorServicio_Direccion,
ps.ProveedorServicio_Email,
t.TipoProveedor_Descripcion,
CONCAT(p.Personal_Apellidos,' '+p.Personal_Nombres) as personals
from proveedor_servicio as ps 
INNER JOIN personal as p on p.Personal_Id=ps.Personal_Id 
INNER JOIN tipo_proveedor as t on ps.TipoProveedor_Id=t.TipoProveedor_Id
WHERE t.TipoProveedor_Descripcion='POR FACTURACION' and
ps.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProveedorServicio_MostrarTodosPorLike_sp` (IN `estado` INT, IN `buscar` VARCHAR(50))  NO SQL
SELECT ps.ProveedorServicio_Id,
ps.ProveedorServicio_Razon_Social,
ps.ProveedorServicio_Ruc,
ps.ProveedorServicio_Entidad,
ps.ProveedorServicio_Cuenta,
ps.ProveedorServicio_Telefono,
ps.ProveedorServicio_Direccion,
ps.ProveedorServicio_Email,
CONCAT(p.Personal_Apellidos,' '+p.Personal_Nombres) as personals
from proveedor_servicio as ps INNER JOIN personal as p on p.Personal_Id=ps.Personal_Id WHERE ps.Estado_Id=estado and concat(ps.ProveedorServicio_Razon_Social,ps.ProveedorServicio_Ruc) like concat('%',buscar,'%')$$

DROP PROCEDURE IF EXISTS `ProveedorServicio_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProveedorServicio_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT ps.ProveedorServicio_Id,
ps.ProveedorServicio_Razon_Social,
ps.ProveedorServicio_Ruc,
ps.ProveedorServicio_Entidad,
ps.ProveedorServicio_Cuenta,
ps.ProveedorServicio_Telefono,
ps.ProveedorServicio_Direccion,
ps.ProveedorServicio_Email,
t.TipoProveedor_Descripcion,
CONCAT(p.Personal_Apellidos,' '+p.Personal_Nombres) as personals
from proveedor_servicio as ps 
INNER JOIN personal as p on p.Personal_Id=ps.Personal_Id 
INNER JOIN tipo_proveedor as t on ps.TipoProveedor_Id=t.TipoProveedor_Id
WHERE ps.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `Reintegro_Agregar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Reintegro_Agregar_sp` (IN `Reintegro_Fecha` DATE, IN `ClienteEntrante_Id` INT, IN `Procedencia_Id` INT, IN `Reintegro_Lote` CHAR(50), IN `Reintegro_Tmh` DECIMAL(9,3), IN `Reintegro_H2o` DECIMAL(9,2), IN `Reintegro_LeyAu` DECIMAL(9,3), IN `Reintegro_LeyAg` DECIMAL(9,3), IN `Reintegro_Inter` DECIMAL(9,2), IN `Reintegro_Rec` DECIMAL(9,2), IN `Reintegro_Maquilla` DECIMAL(9,2), IN `Reintegro_Factor` DECIMAL(9,4), IN `Reintegro_Conscon` DECIMAL(9,2), IN `Reintegro_Escalador` DECIMAL(9,2), IN `Reintegro_Stms` DECIMAL(9,2), IN `Reintegro_TotalUs` DECIMAL(9,2), IN `Estado_Id` INT, IN `Liquidacion_Id` INT, IN `Reintegro_Reintegro` DECIMAL(9,3), IN `Reintegro_Pago` CHAR(50), IN `Reintegro_Tms` DECIMAL(9,3))  NO SQL
INSERT INTO `reintegro`( `Reintegro_Fecha`, `ClienteEntrante_Id`, `Procedencia_Id`, `Reintegro_Lote`, `Reintegro_Tmh`, `Reintegro_H2o`, `Reintegro_LeyAu`, `Reintegro_LeyAg`, `Reintegro_Inter`, `Reintegro_Rec`, `Reintegro_Maquilla`, `Reintegro_Factor`, `Reintegro_Conscon`, `Reintegro_Escalador`, `Reintegro_Stms`, `Reintegro_TotalUs`, `Estado_Id`, `Liquidacion_Id`, `Reintegro_Reintegro`, `Reintegro_Pago`, `Reintegro_Tms`)
VALUES (Reintegro_Fecha, ClienteEntrante_Id, Procedencia_Id, Reintegro_Lote, Reintegro_Tmh, Reintegro_H2o, Reintegro_LeyAu, Reintegro_LeyAg, Reintegro_Inter, Reintegro_Rec, Reintegro_Maquilla, Reintegro_Factor, Reintegro_Conscon, Reintegro_Escalador, Reintegro_Stms, Reintegro_TotalUs, Estado_Id, Liquidacion_Id, Reintegro_Reintegro, Reintegro_Pago, Reintegro_Tms)$$

DROP PROCEDURE IF EXISTS `Reintegro_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Reintegro_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT 
r.Reintegro_Id,
r.Reintegro_Fecha,
r.ClienteEntrante_Id,
r.Procedencia_Id,
r.Reintegro_Lote,
r.Reintegro_Tmh,
r.Reintegro_Tmh,
r.Reintegro_H2o,
r.Reintegro_LeyAu,
r.Reintegro_LeyAg,
r.Reintegro_Inter,
r.Reintegro_Inter,
r.Reintegro_Rec,
r.Reintegro_Maquilla,
r.Reintegro_Factor,
r.Reintegro_Conscon,
r.Reintegro_Escalador,
r.Reintegro_Stms,
r.Reintegro_TotalUs,
r.Estado_Id,
r.Liquidacion_Id,
r.Reintegro_Reintegro,
r.Reintegro_Pago,
r.Reintegro_Tms
from reintegro as r WHERE r.Reintegro_Id=codigo$$

DROP PROCEDURE IF EXISTS `Reintegro_buscarReintegroPorLote_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Reintegro_buscarReintegroPorLote_sp` (IN `lote` CHAR(50))  NO SQL
SELECT
r.Reintegro_Id,
r.Reintegro_Lote,
r.Reintegro_Tms,
r.Reintegro_LeyAu,
r.Reintegro_Inter,
r.Reintegro_Stms,
r.Reintegro_TotalUs,
r.Reintegro_Reintegro

from reintegro as r WHERE r.Reintegro_Lote=lote$$

DROP PROCEDURE IF EXISTS `Reintegro_Eliminar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Reintegro_Eliminar_sp` (IN `codigo` INT, IN `liquidacion` INT)  NO SQL
DELETE from reintegro WHERE Reintegro_Id=codigo$$

DROP PROCEDURE IF EXISTS `Reintegro_Modificar_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Reintegro_Modificar_sp` (IN `Reintegro_Fecha` DATE, IN `ClienteEntrante_Id` INT, IN `Procedencia_Id` INT, IN `Reintegro_Lote` CHAR(50), IN `Reintegro_Tmh` DECIMAL(9,3), IN `Reintegro_H2o` DECIMAL(9,2), IN `Reintegro_LeyAu` DECIMAL(9,2), IN `Reintegro_LeyAg` DECIMAL(9,3), IN `Reintegro_Inter` DECIMAL(9,2), IN `Reintegro_Rec` DECIMAL(9,2), IN `Reintegro_Maquilla` DECIMAL(9,2), IN `Reintegro_Factor` DECIMAL(9,4), IN `Reintegro_Conscon` DECIMAL(9,2), IN `Reintegro_Escalador` DECIMAL(9,2), IN `Reintegro_Stms` DECIMAL(9,2), IN `Reintegro_TotalUs` DECIMAL(9,2), IN `Estado_Id` INT, IN `Reintegro_Reintegro` DECIMAL(9,3), IN `Reintegro_Pago` CHAR(50), IN `Reintegro_Tms` DECIMAL(9,3), IN `codigo` INT)  NO SQL
UPDATE `reintegro` SET 
`Reintegro_Fecha`=Reintegro_Fecha,
`ClienteEntrante_Id`=ClienteEntrante_Id,
`Procedencia_Id`=Procedencia_Id,
`Reintegro_Lote`=Reintegro_Lote,
`Reintegro_Tmh`=Reintegro_Tmh,
`Reintegro_H2o`=Reintegro_H2o,
`Reintegro_LeyAu`=Reintegro_LeyAu,
`Reintegro_LeyAg`=Reintegro_LeyAg,
`Reintegro_Inter`=Reintegro_Inter,
`Reintegro_Rec`=Reintegro_Rec,
`Reintegro_Maquilla`=Reintegro_Maquilla,
`Reintegro_Factor`=Reintegro_Factor,
`Reintegro_Conscon`=Reintegro_Conscon,
`Reintegro_Escalador`=Reintegro_Escalador,
`Reintegro_Stms`=Reintegro_Stms,
`Reintegro_TotalUs`=Reintegro_TotalUs,
`Estado_Id`=Estado_Id,
`Liquidacion_Id`=Liquidacion_Id,
`Reintegro_Reintegro`=Reintegro_Reintegro,
`Reintegro_Pago`=Reintegro_Pago,
`Reintegro_Tms`=Reintegro_Tms WHERE `Reintegro_Id`=codigo$$

DROP PROCEDURE IF EXISTS `Reintegro_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `Reintegro_MostrarTodos_sp` (IN `codigo` INT)  NO SQL
SELECT 
r.Reintegro_Id,
r.Reintegro_Fecha,
concat(ce.ClienteEntrante_Apellidos,' ',ce.ClienteEntrante_Nombres) as ClienteEntrante,
p.Procedencia_Descripcion,
r.Reintegro_Lote,
r.Reintegro_Tmh,
r.Reintegro_H2o,
r.Reintegro_LeyAu,
r.Reintegro_LeyAg,
r.Reintegro_Inter,
r.Reintegro_Rec,
r.Reintegro_Maquilla,
r.Reintegro_Factor,
r.Reintegro_Conscon,
r.Reintegro_Escalador,
r.Reintegro_Stms,
r.Reintegro_TotalUs,
e.Estado_Descripcion,
r.Reintegro_Pago


from reintegro as r INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=r.ClienteEntrante_Id INNER JOIN procedencia as p on p.Procedencia_Id=r.Procedencia_Id INNER JOIN estado as e on e.Estado_Id=r.Estado_Id WHERE r.Liquidacion_Id=codigo$$

DROP PROCEDURE IF EXISTS `RutaReporte_ObtenerDatos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `RutaReporte_ObtenerDatos_sp` (IN `empresa` INT)  NO SQL
SELECT 
rr.ReporteRuta_Id,
rr.RutaReporte_AdelantoCliente,
rr.RutaReporte_AdelantoProveedor,
rr.RutaReporte_Cheque,
rr.RutaReporte_Factura,
rr.RutaReporte_Liquidacion,
rr.RutaReporte_PagoTransporte,
rr.RutaReporte_Valorizacion,
rr.RutaReporte_NotaDebito,
rr.RutaReporte_NotaCredito

from reporte_ruta as rr WHERE rr.Empresa_Id=empresa$$

DROP PROCEDURE IF EXISTS `TareaDiaria_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_Agregar_sp` (IN `empresa` INT, IN `personal` INT, IN `fecha` DATE, IN `descripcion` VARCHAR(450), IN `estado` INT)  NO SQL
INSERT INTO `tarea_diaria`(`TareaDiaria_Descripcion`, `Empresa_Id`, `Personal_Id`,  `TareaDiaria_Fecha`,Estado_Id) VALUES(descripcion,empresa,personal,fecha,estado)$$

DROP PROCEDURE IF EXISTS `TareaDiaria_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT
td.TareaDiaria_Id,
td.TareaDiaria_Descripcion,
td.TareaDiaria_Fecha,
td.Estado_Id
from tarea_diaria as td WHERE td.TareaDiaria_Id=codigo$$

DROP PROCEDURE IF EXISTS `TareaDiaria_CambiarEstadoNotificacion_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_CambiarEstadoNotificacion_sp` (IN `codigo` INT, IN `enoti` VARCHAR(50))  NO SQL
UPDATE tarea_diaria as td SET td.TareaDiaria_EstadoNotificacion=enoti WHERE td.TareaDiaria_Id=codigo$$

DROP PROCEDURE IF EXISTS `TareaDiaria_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from tarea_diaria WHERE TareaDiaria_Id=codigo$$

DROP PROCEDURE IF EXISTS `TareaDiaria_ModificarPorEstado_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_ModificarPorEstado_sp` (IN `codigo` INT, IN `estado` INT)  NO SQL
UPDATE tarea_diaria as td SET td.Estado_Id=estado WHERE td.TareaDiaria_Id=codigo$$

DROP PROCEDURE IF EXISTS `TareaDiaria_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_Modificar_sp` (IN `codigo` INT, IN `descripcion` VARCHAR(450), IN `fecha` DATE, IN `estado` INT)  NO SQL
UPDATE tarea_diaria as td SET td.TareaDiaria_Descripcion=descripcion,td.TareaDiaria_Fecha=fecha,td.Estado_Id=estado WHERE td.TareaDiaria_Id=codigo$$

DROP PROCEDURE IF EXISTS `TareaDiaria_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT 
td.TareaDiaria_Id,
td.TareaDiaria_Descripcion,
td.TareaDiaria_Fecha,
e.Estado_Descripcion

from tarea_diaria as td INNER JOIN estado as e on e.Estado_Id=td.Estado_Id WHERE td.Estado_Id=estado ORDER by td.TareaDiaria_Fecha ASC$$

DROP PROCEDURE IF EXISTS `TareaDiaria_ObtenerListaTareasPorHacer_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TareaDiaria_ObtenerListaTareasPorHacer_sp` ()  NO SQL
SELECT 
td.TareaDiaria_Id,
td.TareaDiaria_Descripcion,
td.TareaDiaria_Fecha,
td.Personal_Id,
td.TareaDiaria_EstadoNotificacion,
td.Estado_Id
from tarea_diaria as td$$

DROP PROCEDURE IF EXISTS `TipoCliente_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoCliente_Agregar_sp` (IN `descripcion` VARCHAR(200), IN `personal` INT)  NO SQL
INSERT INTO tipo_cliente(TipoCliente_Descripcion,Personal_Id) VALUES(descripcion,personal)$$

DROP PROCEDURE IF EXISTS `TipoCliente_buscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoCliente_buscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT tc.TipoCliente_Id,tc.TipoCliente_Descripcion from tipo_cliente as tc WHERE tc.TipoCliente_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoCliente_BuscarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoCliente_BuscarTodosPorLike_sp` (IN `de` VARCHAR(100))  NO SQL
SELECT tc.TipoCliente_Id,tc.TipoCliente_Descripcion FROM tipo_cliente as tc where tc.Estado_Id=1 and tc.TipoCliente_Descripcion like concat('%',de,'%')$$

DROP PROCEDURE IF EXISTS `TipoCliente_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoCliente_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from tipo_cliente WHERE TipoCliente_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoCliente_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoCliente_Modificar_sp` (IN `codigo` INT, IN `descripcion` VARCHAR(200))  NO SQL
UPDATE tipo_cliente SET TipoCliente_Descripcion=descripcion where TipoCliente_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoCliente_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoCliente_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT tc.TipoCliente_Id,tc.TipoCliente_Descripcion FROM tipo_cliente as tc where tc.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `TipoFacturaElectronica_MostrarTodos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoFacturaElectronica_MostrarTodos` (IN `estado` INT)  NO SQL
SELECT tp.TipoFacturaElectronica_Id,tp.TipoFacturaElectronica_Descripcion from tipo_factura_electronica as tp WHERE tp.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `TipoNotaCredito_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `TipoNotaCredito_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT tnc.TipoNotaCredito_id,tnc.TipoNotaCredito_descripcion from tipo_nota_credito as tnc WHERE tnc.TipoNotaCredito_id=codigo$$

DROP PROCEDURE IF EXISTS `TipoNotaCredito_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `TipoNotaCredito_MostrarTodos_sp` ()  NO SQL
SELECT tnc.TipoNotaCredito_id,tnc.TipoNotaCredito_descripcion from tipo_nota_credito as tnc WHERE tnc.Estado_Id=1$$

DROP PROCEDURE IF EXISTS `TipoNotaDebito_BuscarPorCodigo_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `TipoNotaDebito_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT tnc.TipoNotaDebito_Id,tnc.TipoNotaDebito_Descripcion from tipo_nota_debito as tnc WHERE tnc.TipoNotaDebito_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoNotaDebito_MostrarTodos_sp`$$
CREATE DEFINER=`levi`@`%` PROCEDURE `TipoNotaDebito_MostrarTodos_sp` ()  NO SQL
SELECT tnc.TipoNotaDebito_Id,tnc.TipoNotaDebito_Descripcion from tipo_nota_debito as tnc WHERE tnc.Estado_Id=1$$

DROP PROCEDURE IF EXISTS `TipoPersonal_Actualizar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoPersonal_Actualizar_sp` (IN `codigo` INT, IN `descripcion` VARCHAR(200), IN `estados` INT)  NO SQL
UPDATE tipo_personal SET TipoPersonal_Descripcion=descripcion,Estado_Id=estados where TipoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoPersonal_agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoPersonal_agregar_sp` (IN `descripcion` VARCHAR(200), IN `estado` INT)  NO SQL
INSERT INTO tipo_personal(TipoPersonal_Descripcion,Estado_Id) values(descripcion,estado)$$

DROP PROCEDURE IF EXISTS `TipoPersonal_buscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoPersonal_buscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT tp.TipoPersonal_Id,tp.TipoPersonal_Descripcion from tipo_personal as tp WHERE tp.TipoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoPersonal_BuscarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoPersonal_BuscarTodosPorLike_sp` (IN `de` VARCHAR(100))  NO SQL
SELECT tp.TipoPersonal_Id, tp.TipoPersonal_Descripcion from tipo_personal as tp where tp.Estado_Id=1 and tp.TipoPersonal_Descripcion like CONCAT('%', de , '%')$$

DROP PROCEDURE IF EXISTS `TipoPersonal_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoPersonal_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from tipo_personal WHERE TipoPersonal_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoPersonal_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoPersonal_MostrarTodos_sp` (IN `estado` INT)  NO SQL
SELECT tp.TipoPersonal_Id, tp.TipoPersonal_Descripcion from tipo_personal as tp where tp.Estado_Id=estado$$

DROP PROCEDURE IF EXISTS `TipoProveedor_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoProveedor_Agregar_sp` (IN `descripcion` VARCHAR(64), IN `personal` INT)  NO SQL
INSERT INTO tipo_proveedor(TipoProveedor_Descripcion,Personal_Id) VALUES(descripcion,personal)$$

DROP PROCEDURE IF EXISTS `TipoProveedor_BuscarPorCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoProveedor_BuscarPorCodigo_sp` (IN `codigo` INT)  NO SQL
SELECT tp.TipoProveedor_Id,tp.TipoProveedor_Descripcion,tp.Personal_Id from tipo_proveedor as tp WHERE tp.TipoProveedor_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoProveedor_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoProveedor_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from tipo_proveedor WHERE TipoProveedor_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoProveedor_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoProveedor_Modificar_sp` (IN `descripcion` VARCHAR(64), IN `personal` INT, IN `codigo` INT)  NO SQL
UPDATE tipo_proveedor as tp set tp.TipoProveedor_Descripcion=descripcion,tp.Personal_Id=personal where tp.TipoProveedor_Id=codigo$$

DROP PROCEDURE IF EXISTS `TipoProveedor_MostrarTodosPorLike_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoProveedor_MostrarTodosPorLike_sp` (IN `estado` INT, IN `texto` VARCHAR(50))  NO SQL
SELECT tp.TipoProveedor_Id,tp.TipoProveedor_Descripcion,
concat(p.Personal_Apellidos,p.Personal_Nombres) as personal from tipo_proveedor as tp INNER JOIN personal as p on p.Personal_Id=tp.Personal_Id  WHERE tp.Estado_Id=estado and tp.TipoProveedor_Descripcion like concat('%',texto,'%')$$

DROP PROCEDURE IF EXISTS `TipoProveedor_MostrarTodos_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `TipoProveedor_MostrarTodos_sp` (IN `codigo` INT)  NO SQL
SELECT tp.TipoProveedor_Id,tp.TipoProveedor_Descripcion,
concat(p.Personal_Apellidos,p.Personal_Nombres) as personal from tipo_proveedor as tp INNER JOIN personal as p on p.Personal_Id=tp.Personal_Id  WHERE tp.Estado_Id=codigo$$

DROP PROCEDURE IF EXISTS `Valorizacion_Agregar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Valorizacion_Agregar_sp` (IN `clientes` INT, IN `costoTraTrujSoles` DECIMAL(9,2), IN `costoTraNascaSoles` DECIMAL(9,2), IN `totalAnalisis` DECIMAL(9,2), IN `totalUs` DECIMAL(9,2), IN `totalPorcentaje` DECIMAL(9,2), IN `adelanto` DECIMAL(9,2), IN `otrGastos` DECIMAL(9,2), IN `totalGastos` DECIMAL(9,2), IN `totalPagar` DECIMAL(9,2), IN `estadov` VARCHAR(20), IN `cambios` DECIMAL(9,2), IN `costoTraTruDolar` DECIMAL(9,2), IN `costoTraNascaDolar` DECIMAL(9,2), IN `cLotes` DECIMAL(9,2), IN `fechaId` DATE, IN `descuentoSoles` DECIMAL(9,2), IN `tmh` DECIMAL(9,2), IN `tarifaPorcentaje` DECIMAL(9,2), IN `policia` DECIMAL(9,2), IN `tarifaAnalisis` DECIMAL(9,2), IN `toneladas` DECIMAL(9,2))  NO SQL
INSERT INTO `valorizacion`( `ClienteEntrante_Id`, `Valorizacion_Tmh`, `Valorizacion_CostoTraTrujiSoles`, `Valorizacion_CostTraNasSoles`, `Valorizacion_TotalAnalisis`, `Valorizacion_TotalUS`, `Valorizacion_TarifaPorcentaje`, `Valorizacion_Adelanto`, `Valorizacion_OtrosGastos`, `Valorizacion_TotalGastos`, `Valorizacion_TotalPagar`, `Valorizacion_Estado`, `Valorizacion_Cambio`, `Valorizacion_CostoTraTruDolar`, `Valorizacion_CostTraNascDolar`, `Valorizacion_CLotes`, `Valorizacion_TotalPorcentaje`, `Valorizacion_FechaId`, `Valorizacion_DescuentoSoles`, `Valoracion_Policia`, `Valoracion_TarifaAnalisis`,Valorizacion_Toneladas) VALUES (clientes,tmh,costoTraTrujSoles,costoTraNascaSoles,totalAnalisis,totalUs,tarifaPorcentaje,adelanto,otrGastos,totalGastos,totalPagar,estadov,cambios,costoTraTruDolar,
costoTraNascaDolar,cLotes,totalPorcentaje,fechaId,descuentoSoles,policia,tarifaAnalisis,toneladas)$$

DROP PROCEDURE IF EXISTS `Valorizacion_BuscarPorCodigoCliente_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Valorizacion_BuscarPorCodigoCliente_sp` (IN `codigo` INT, IN `fecha` VARCHAR(50))  NO SQL
SELECT
v.Valorizacion_codigo,
v.ClienteEntrante_Id,
v.Valorizacion_Tmh,
v.Valorizacion_CostoTraTrujiSoles,
v.Valorizacion_CostTraNasSoles,
v.Valorizacion_TotalAnalisis,
v.Valorizacion_TotalUS,
v.Valorizacion_TotalPorcentaje,
v.Valorizacion_Adelanto,
v.Valorizacion_OtrosGastos,
v.Valorizacion_TotalGastos,
v.Valorizacion_TotalPagar,
v.Valorizacion_Estado,
v.Valorizacion_Cambio,
v.Valorizacion_CostoTraTruDolar,
v.Valorizacion_CostTraNascDolar,
v.Valorizacion_CLotes,
v.Valorizacion_TotalPorcentaje,
v.Valorizacion_FechaId,
v.Valorizacion_DescuentoSoles,
v.Valoracion_TarifaAnalisis,
v.Valorizacion_CLotes,
v.Valorizacion_TarifaPorcentaje
from valorizacion as v INNER JOIN cliente_entrante as ce on ce.ClienteEntrante_Id=v.ClienteEntrante_Id WHERE ce.ClienteEntrante_Id=codigo and v.Valorizacion_FechaId=fecha and v.Estado_Id=1$$

DROP PROCEDURE IF EXISTS `Valorizacion_Buscar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Valorizacion_Buscar_sp` (IN `codigo` INT)  NO SQL
SELECT 
v.Valorizacion_codigo,
v.ClienteEntrante_Id,
v.Valorizacion_Tmh,
v.Valorizacion_CostoTraTrujiSoles,
v.Valorizacion_CostTraNasSoles,
v.Valorizacion_TotalAnalisis,
v.Valorizacion_TotalUS,
v.Valorizacion_TotalPorcentaje,
v.Valorizacion_Adelanto,
v.Valorizacion_OtrosGastos,
v.Valorizacion_TotalGastos,
v.Valorizacion_TotalPagar,
v.Valorizacion_Estado,
v.Valorizacion_Cambio,
v.Valorizacion_CostoTraTruDolar,
v.Valorizacion_CostTraNascDolar,
v.Valorizacion_CLotes,
v.Valorizacion_TotalPorcentaje,
v.Valorizacion_FechaId,
v.Valorizacion_DescuentoSoles,
v.Valoracion_TarifaAnalisis,
v.Valorizacion_TarifaPorcentaje,
v.Valoracion_Policia,
v.Valorizacion_Toneladas

from valorizacion as v WHERE v.Valorizacion_codigo=codigo$$

DROP PROCEDURE IF EXISTS `Valorizacion_Eliminar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Valorizacion_Eliminar_sp` (IN `codigo` INT)  NO SQL
DELETE from valorizacion WHERE Valorizacion_codigo=codigo$$

DROP PROCEDURE IF EXISTS `Valorizacion_Modificar_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Valorizacion_Modificar_sp` (IN `clientes` INT, IN `costoTraTrujSoles` DECIMAL(9,2), IN `costoTraNascaSoles` DECIMAL(9,2), IN `totalAnalisis` DECIMAL(9,2), IN `totalUs` DECIMAL(9,2), IN `totalPorcentaje` DECIMAL(9,2), IN `adelanto` DECIMAL(9,2), IN `otrGastos` DECIMAL(9,2), IN `totalGastos` DECIMAL(9,2), IN `totalPagar` DECIMAL(9,2), IN `cambios` DECIMAL(9,2), IN `costoTraTruDolar` DECIMAL(9,2), IN `costoTraNascaDolar` DECIMAL(9,2), IN `cLotes` DECIMAL(9,2), IN `fechaId` DATE, IN `descuentoSoles` DECIMAL(9,2), IN `tmh` DECIMAL(9,2), IN `tarifaPorcentaje` DECIMAL(9,2), IN `policia` DECIMAL(9,2), IN `tarifaAnalisis` DECIMAL(9,2), IN `toneladas` DECIMAL(9,2), IN `codigo` INT)  NO SQL
UPDATE  valorizacion SET `ClienteEntrante_Id` =clientes, `Valorizacion_Tmh`=tmh, `Valorizacion_CostoTraTrujiSoles`=costoTraTrujSoles, `Valorizacion_CostTraNasSoles`=costoTraNascaSoles, `Valorizacion_TotalAnalisis`=totalAnalisis, `Valorizacion_TotalUS`=totalUs, `Valorizacion_TarifaPorcentaje`=tarifaPorcentaje, `Valorizacion_Adelanto`=adelanto, `Valorizacion_OtrosGastos`=otrGastos, `Valorizacion_TotalGastos`=totalGastos, `Valorizacion_TotalPagar`=totalPagar,  `Valorizacion_Cambio`=cambios, `Valorizacion_CostoTraTruDolar`=costoTraTruDolar, `Valorizacion_CostTraNascDolar`=costoTraNascaDolar, `Valorizacion_CLotes`=cLotes, `Valorizacion_TotalPorcentaje`=totalPorcentaje, `Valorizacion_FechaId`=fechaId, `Valorizacion_DescuentoSoles`=descuentoSoles, `Valoracion_Policia`=policia, `Valoracion_TarifaAnalisis`=tarifaAnalisis,Valorizacion_Toneladas=toneladas where Valorizacion_codigo=codigo$$

DROP PROCEDURE IF EXISTS `Valorizacion_ObtenerUltimoCodigo_sp`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Valorizacion_ObtenerUltimoCodigo_sp` ()  NO SQL
SELECT `AUTO_INCREMENT` as codigo
FROM  INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'bdsistemayersica'
AND   TABLE_NAME   = 'valorizacion'$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `adelanto`
--

DROP TABLE IF EXISTS `adelanto`;
CREATE TABLE IF NOT EXISTS `adelanto` (
  `Adelanto_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Adelanto_Cantidad` decimal(9,2) DEFAULT NULL,
  `Adelanto_Moneda` char(50) NOT NULL,
  `Adelanto_Descripcion` varchar(200) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '8',
  `Adelanto_Fecha` date NOT NULL,
  `ClienteEntrante_Id` int(11) DEFAULT NULL,
  `ProveedorServicio_Id` int(11) DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Adelanto_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Adelanto_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Adelanto_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Adeelanto_Estado` varchar(50) DEFAULT NULL,
  `Valorizacion_Id` int(11) DEFAULT NULL,
  `Empresa_Id` int(11) NOT NULL,
  PRIMARY KEY (`Adelanto_Id`),
  KEY `asdsadas` (`Estado_Id`),
  KEY `as1dsadas` (`ClienteEntrante_Id`),
  KEY `as1ds11adas` (`ProveedorServicio_Id`),
  KEY `as1ds11adas22` (`Personal_Id`),
  KEY `asdasdasdasdaaa` (`Empresa_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `adelanto_personal`
--

DROP TABLE IF EXISTS `adelanto_personal`;
CREATE TABLE IF NOT EXISTS `adelanto_personal` (
  `AdelantoPersonal_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Contrato_Id` int(11) NOT NULL,
  `AdelantoPersonal_Fecha` date NOT NULL,
  `AdelantoPersonal_Motivo` varchar(250) NOT NULL,
  `AdelantoPersonal_Monto` decimal(9,2) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '8',
  `Empresa_Id` int(11) NOT NULL,
  `PagoPersonal_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`AdelantoPersonal_Id`),
  KEY `aaaaaa` (`Estado_Id`),
  KEY `holaaaa` (`Contrato_Id`),
  KEY `holaaaaaas` (`Empresa_Id`),
  KEY `asdadasss` (`PagoPersonal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `agrupadorfe`
--

DROP TABLE IF EXISTS `agrupadorfe`;
CREATE TABLE IF NOT EXISTS `agrupadorfe` (
  `AgrupadorFE_Id` int(11) NOT NULL AUTO_INCREMENT,
  `AgrupadorFE_Orden` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `AgrupadorFE_Fecha` varchar(50) NOT NULL,
  PRIMARY KEY (`AgrupadorFE_Id`),
  KEY `sadasdasd` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agrupadorfe`
--

INSERT INTO `agrupadorfe` (`AgrupadorFE_Id`, `AgrupadorFE_Orden`, `Estado_Id`, `AgrupadorFE_Fecha`) VALUES
(60, 1, 1, '2019-04-07'),
(61, 1, 1, '2019-04-09'),
(62, 2, 1, '2019-04-09'),
(63, 3, 1, '2019-04-09'),
(64, 4, 1, '2019-04-09'),
(65, 5, 1, '2019-04-09'),
(66, 6, 1, '2019-04-09'),
(67, 1, 1, '2019-04-08'),
(68, 2, 1, '2019-04-08'),
(69, 3, 1, '2019-04-08'),
(70, 4, 1, '2019-04-08'),
(71, 5, 1, '2019-04-08'),
(72, 6, 1, '2019-04-08'),
(73, 7, 1, '2019-04-08'),
(74, 8, 1, '2019-04-08'),
(75, 9, 1, '2019-04-08'),
(76, 10, 1, '2019-04-08'),
(77, 1, 1, '2019-04-12'),
(78, 2, 1, '2019-04-12'),
(79, 3, 1, '2019-04-12'),
(80, 4, 1, '2019-04-12'),
(81, 5, 1, '2019-04-12'),
(82, 6, 1, '2019-04-12'),
(83, 1, 1, '2019-05-18'),
(84, 2, 1, '2019-05-18'),
(85, 3, 1, '2019-05-18'),
(86, 4, 1, '2019-05-18'),
(87, 5, 1, '2019-05-18'),
(88, 6, 1, '2019-05-18'),
(89, 7, 1, '2019-05-18');

-- --------------------------------------------------------

--
-- Table structure for table `agrupadornce`
--

DROP TABLE IF EXISTS `agrupadornce`;
CREATE TABLE IF NOT EXISTS `agrupadornce` (
  `AgrupadoNCE_Id` int(11) NOT NULL AUTO_INCREMENT,
  `AgrupadorNCE_Orden` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `AgrupadoNCE_Fecha` varchar(50) NOT NULL,
  PRIMARY KEY (`AgrupadoNCE_Id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agrupadornce`
--

INSERT INTO `agrupadornce` (`AgrupadoNCE_Id`, `AgrupadorNCE_Orden`, `Estado_Id`, `AgrupadoNCE_Fecha`) VALUES
(1, 3, 1, '2019-04-07'),
(2, 1, 1, '2019-04-08'),
(3, 2, 1, '2019-04-08'),
(4, 3, 1, '2019-04-08'),
(5, 4, 1, '2019-04-08'),
(6, 5, 1, '2019-04-08'),
(7, 6, 1, '2019-04-08'),
(8, 7, 1, '2019-04-08'),
(9, 8, 1, '2019-04-08'),
(10, 9, 1, '2019-04-08'),
(11, 10, 1, '2019-04-08'),
(12, 2, 1, '2019-04-05'),
(13, 3, 1, '2019-04-05');

-- --------------------------------------------------------

--
-- Table structure for table `agrupadornde`
--

DROP TABLE IF EXISTS `agrupadornde`;
CREATE TABLE IF NOT EXISTS `agrupadornde` (
  `AgrupadorNDE_Id` int(11) NOT NULL AUTO_INCREMENT,
  `AgrupadorNDE_Orden` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `AgrupadoNDE_Fecha` varchar(100) NOT NULL,
  PRIMARY KEY (`AgrupadorNDE_Id`),
  KEY `asdasdasdad` (`Estado_Id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agrupadornde`
--

INSERT INTO `agrupadornde` (`AgrupadorNDE_Id`, `AgrupadorNDE_Orden`, `Estado_Id`, `AgrupadoNDE_Fecha`) VALUES
(1, 1, 1, '2019-04-07'),
(2, 2, 1, '2019-04-07'),
(3, 1, 1, '2019-04-05');

-- --------------------------------------------------------

--
-- Table structure for table `cambios`
--

DROP TABLE IF EXISTS `cambios`;
CREATE TABLE IF NOT EXISTS `cambios` (
  `Cambios_Dolar` decimal(9,2) DEFAULT NULL,
  `Cambios_Tarifa` decimal(9,2) DEFAULT NULL,
  `Cambios_Tarifaa` decimal(9,2) DEFAULT NULL,
  `Cambios_Trans1` decimal(9,2) DEFAULT NULL,
  `Cambios_Trans2` decimal(9,2) DEFAULT NULL,
  `Cambios_Poli` decimal(9,2) DEFAULT NULL,
  `Cambios_Tonelada` decimal(9,2) DEFAULT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Cambios_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Cambios_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Cambios_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Cambios_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`Cambios_Id`),
  KEY `asdasd` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cambios`
--

INSERT INTO `cambios` (`Cambios_Dolar`, `Cambios_Tarifa`, `Cambios_Tarifaa`, `Cambios_Trans1`, `Cambios_Trans2`, `Cambios_Poli`, `Cambios_Tonelada`, `Estado_Id`, `Cambios_Id`, `Cambios_Fecha_Registrado`, `Cambios_Fecha_Actualizado`, `Cambios_Fecha_Eliminado`) VALUES
('3.30', '2.00', '29.50', '170.00', '220.00', '900.00', '29.00', 1, 1, '2019-03-07 23:00:23', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `cheque`
--

DROP TABLE IF EXISTS `cheque`;
CREATE TABLE IF NOT EXISTS `cheque` (
  `Cheque_Id` int(11) NOT NULL AUTO_INCREMENT,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `Cheque_EntregadoA` varchar(100) NOT NULL,
  `Cheque_Concepto` varchar(250) NOT NULL,
  `Cheque_Monto` decimal(9,2) NOT NULL,
  `Cheque_Moneda` char(8) NOT NULL,
  `Cheque_FechaPago` varchar(50) DEFAULT NULL,
  `Cheque_FechaEmision` varchar(50) DEFAULT NULL,
  `Cheque_Lectura` varchar(400) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Empresa_Id` int(11) NOT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`Cheque_Id`),
  KEY `asdasdasdas` (`Empresa_Id`),
  KEY `asdasdasdssas` (`Estado_Id`),
  KEY `asdasdasdssaas` (`ProveedorServicio_Id`),
  KEY `aaaaaas` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cliente_entrante`
--

DROP TABLE IF EXISTS `cliente_entrante`;
CREATE TABLE IF NOT EXISTS `cliente_entrante` (
  `ClienteEntrante_Id` int(11) NOT NULL AUTO_INCREMENT,
  `ClienteEntrante_DNI` char(8) NOT NULL,
  `ClienteEntrante_Apellidos` varchar(50) NOT NULL,
  `ClienteEntrante_Nombres` varchar(60) NOT NULL,
  `ClienteEntrante_Telefono` char(15) NOT NULL,
  `ClienteEntrante_Direccion` varchar(200) NOT NULL,
  `ClienteEntrante_Sexo` char(1) NOT NULL,
  `TipoCliente_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `ClienteEntrante_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ClienteEntrante_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `ClienteEntrante_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Empresa_Id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ClienteEntrante_Id`),
  UNIQUE KEY `ClienteEntrante_DNI` (`ClienteEntrante_DNI`),
  UNIQUE KEY `ClienteEntrante_DNI_2` (`ClienteEntrante_DNI`),
  KEY `asddas` (`Estado_Id`),
  KEY `aasdassddas` (`Personal_Id`),
  KEY `aasdassadsasddas` (`TipoCliente_Id`),
  KEY `asdassssdasd` (`Empresa_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cliente_entrante`
--

INSERT INTO `cliente_entrante` (`ClienteEntrante_Id`, `ClienteEntrante_DNI`, `ClienteEntrante_Apellidos`, `ClienteEntrante_Nombres`, `ClienteEntrante_Telefono`, `ClienteEntrante_Direccion`, `ClienteEntrante_Sexo`, `TipoCliente_Id`, `Estado_Id`, `ClienteEntrante_Fecha_Registrado`, `ClienteEntrante_Fecha_Actualizado`, `ClienteEntrante_Fecha_Eliminado`, `Personal_Id`, `Empresa_Id`) VALUES
(1, '19435007', 'CRUZ SIFUENTES', 'JUSTINA', '956334984', 'TRUJILLO', 'F', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(2, '32872847', 'QUEZADA SEVILLANO', 'ANDRES', '943805789', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(3, '44260666', 'VERA CRUZADO', 'ESEQUIEL', '948732417', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(4, '19420583', 'LARA LOPEZ', 'VICTORIA', '0', 'LA SOLEDAD', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(5, '19435195', 'DIAZ SILVA', 'NELVER LUIS', '984338056', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(6, '4812241', 'DIAZ ORIGUELA', 'BRIGUITE', '978692411', 'RETAMAS', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(7, '19570046', 'VEJARANO CAMPOS', 'GERMAN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(8, '19419107', 'BAILON ARAUJO', 'PABLO CIRO', '948356330', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(9, '32533305', 'CORNELLO GARCIA', 'ALBERTO', '944221572', 'SA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(10, '48609180', 'PEÑA FLORES ', 'FLAVIA', '950228512', 'LA SOLEDAD ', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(11, '2458112', 'LALA', 'LALA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(12, '2345523', 'FRODO', 'FRODO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(13, '19406679', 'ARGOMEDO VILLANUEVA', 'REYNALDO', '987606061', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(14, '5845214', 'LOPEZ', 'FELIX', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(15, '9852364', 'CRUZADO TAPIA', 'CARLOS', '', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(16, '658425', 'QUEZADA QUIJANO', 'DIONICIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(17, '658421', 'LARA LOPEZ', 'PEDRO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(18, '6845842', 'MORENO MENDIETA', 'ANTONIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(19, '3581221', 'PONTE', 'IVAN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(20, '3365665', 'TRINIDAD MARREROS', 'PABLO ELADIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(21, '19431936', 'TANTAQUILLA DOMINGUEZ', 'LELIS', '', '', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(22, '41727176', 'VIDAL TORRES', 'JORGE', '953266588', 'AV. CESAR VALLEJO URB. LA RINCONADA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(23, '6582258', 'PONTE PEÑA LENIN', 'LENIN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(24, '3488652', 'LARA LOPEZ', 'ELEUTERIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(25, '3362255', 'CARRUITERO', 'HECTOR', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(26, '36963', 'ASTO', 'ASTO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(27, '19426977', 'PONCE VACA', 'CAYO', '963822874', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(28, '19405046', 'PONTE SANDOVAL', 'ARTEMIO   ROJAS', '940627747', 'TRUJILLO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(29, '255852', 'MARTELL', 'JORGE', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(30, '41171233', 'CHAVEZ FIGEROA', 'JUAN', '942621467', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(31, '345821', 'CHAVEZ FIGUEROA', 'PAUL', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(32, '19415875', 'TERRONES PAJUELO', 'MARINO', '966335183', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(33, '19890102', 'ORTIZ RODRIGUEZ', 'TADEO', '0', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(34, '2233333', 'PALACIOS', 'KEVIN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(35, '19420434', 'GONGORA ESPINOZA', 'FELICIANO', '978384947', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(36, '40469703', 'MALDONADO VILLANUEVA', 'SACRAMENTO', '999526755', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(37, '44444441', 'PALACIOS CARHUAYANQUI', 'FREDDY ', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(38, '788888', 'RODRIGUEZ', 'LEONCIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(39, '45508544', 'TORRES VASQUEZ', 'ADONIAS MOISES PAPEAU', '948709259', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(40, '11854811', 'CASTILLO', 'CASTILLO', '', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(41, '73988649', 'ARAUJO MALDONADO', 'ROISER', '942324453', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(42, '45434551', 'VASQUEZ SANTISTEBAN', 'OMAR', '948057839', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(43, '42598361', 'VASQUEZ MENDIETA', 'EDWIN', '968074712', 'PATAZ', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(44, '42712368', 'CASTILLO RODRIGUEZ', 'RONAL', '976270842', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(45, '32889045', 'PATRICIO WUACA COLQUI', 'HERMITANIO', '966348653', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(46, '19434165', 'MARTINEZ PASTOR', 'VICTOR ', '983096911', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(47, '71987661', 'LARA TANTAQUILLA', 'YERSICA', '993010102', 'LA SOLEDAD', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(48, '32543142', 'CASTAÑEDA HENRRIQUES', 'HERMINIA', '964359943', 'LA SOLEDAD', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(49, '333336', 'CAMPOS ARAUJO', 'ELEUTERIA', '', '', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(50, '42154973', 'MORENO ACUÑA', 'CARLOS', '974612075', 'LA ESPERANZA', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(51, '44780988', 'MENDOZA RUIZ', 'PERCY', '943396026', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(52, '80484006', 'MALDONADO ANTICONA', 'SEFERINO', '966551047', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(53, '19431780', 'MINA', 'PATAZ', '', 'PATAZ', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(54, '06785954', 'HERRADA VASQUEZ', 'PABLO', '950460074', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(55, '88888', 'JHOJANIS', 'JHOJANIS', '111111111', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(56, '45263514', 'POR', 'CAMBIAR', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(57, '73512189', 'REYES LAYZA', 'QUENEDY', '943348200', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(58, '1111111', 'MARCHENA ', 'MARCHENA', '1111111', 'LA SOLEDAD', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(59, '3333333', 'CALDERON', 'SERGIO', '2222222', 'LA SOLEDAD', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(60, '22211111', 'ROSHO', 'ROSHO', '222222222', 'RETAMAZ', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(61, '99999999', 'CHATO', 'CHATO', '9999999', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(62, '44904242', 'SEVILLANO LIÑAN', 'ROSARIO', '953581569', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(63, '45829284', 'MENDOZA VIGO', 'EUSEBIO', '948756941', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(64, '48945928', 'MALDONADO CABALLERO', 'LUIS', '112', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(65, '19403012', 'LOPEZ MEZA', 'JOSE COCAS', '943659937', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(66, '47215787', 'BRICEÑO CUEVA', 'KEIVIN', '22', 'CHILIA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(67, '19434514', 'ARANDA ALTAMIRANO', 'MARIA', '951319799', 'CHUCHUMARAI', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(68, '895662', 'POR ARREGLAR', 'POR ARREGLAR', '4566', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(69, '45938330', 'PIZAN ARANDA', 'GIOBANA', '948281492', 'CHUCHUMARAI', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(70, '41435180', 'DOMINGUEZ PAZ', 'JORGE', '963814977', 'PATAZ', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(71, '5454545', 'PRUEBA', 'PRUEBA', '9868686', 'CSFASDADS', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(72, '20171', 'CHATO', 'CHATO', '20172', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(73, '20172', 'HOMERO', 'HOMERO', '20172', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(74, '20173', 'ADELMAR', 'ADELMAR', '20173', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(75, '45454545', 'COMPRA', 'PATAZ', '957655510', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(76, '41741387', 'CORONEL ALVA', 'EDIN', '977128805', 'PATAZ', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(77, '458532', 'MENDOZA CORNELIO', 'JERSON', '976265653', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(78, '19691624', 'PATRICIO WUACOLQUI', 'SEGUNDO', '997898902', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(79, '41126522', 'CASTAÑEDA CAMPOS', 'HIPOLITO ANTONIO', '992902405', 'BULDIBUYO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(80, '45470745', 'BERMUDEZ PALOMINO', 'JUAN CARLOS', '963180601', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(81, '42526618', 'VERA GERONIMO', 'JORGE LUIS', '948371611', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(82, '5656565', 'EUGENIO', 'EUGENIO', '56565656', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(83, '45444656', 'TANTAQUILLA DOMINGUEZ', 'YANINO', '151542', 'CHILIA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(84, '05151514', 'RONAL', 'RONAL', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(85, '15541550', 'SEGUNDO', 'SEGUNDO', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(86, '15111622', 'JENCHO', 'JENCHO', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(87, '11161118', 'DANTE', 'DANTE', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(88, '40470465', 'ROMERO ALAYO', 'MARCO ANTONIO', '953813029', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(89, '46625226', 'CUEVA MENDIETA', 'JEYNER', '943373653', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(90, '0000000', 'ASTO', 'ASTO', '000000', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(91, '9999999', 'VALDIVIEZO', 'JULIO', '999999', 'SARTINBANBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(92, '44187484', 'TERRONES TORRES', 'CAMILO', '942285231', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(93, '96789532', 'WILSER', 'WILSER', '6665687', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(94, '45843324', 'ANGEL', 'ANGEL', '855622', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(95, '26951031', 'CASTILLO CASTILLO', 'SANTOS MARGARITA', '943399465', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(96, '44040492', 'QUISPE SANTISTEBAN', 'VICTOR', '965470121', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(97, '89984545', 'CARLOS', 'CARLOS', '7895546', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(98, '71112112', 'POLO', 'POLO', '987945211', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(99, '81115121', 'OLGA', 'OLGA', '984211121', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(100, '18421151', 'ROJAS', 'ROJAS', '965521121', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(101, '33819170', 'HUAMAN RAMIREZ', 'JUAN ', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(102, '31635558', 'OSORIO MEJIA', 'DIOGENES', '965473020', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(103, '48395969', 'ROSADO SOTO', 'VERONICA', '961376357', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(104, '47862644', 'MENDOZA MEREGILDO', 'VICTOR', '955758829', 'PATAZ', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(105, '18510194', 'ORTEGA', 'NASAEL', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(106, '42698910', 'ARO LAREDO', 'ROBERTO', '993300281', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(107, '19432542', 'FERNANDEZ MORENO', 'ELESBAN', '94339934', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(108, '46274876', 'PIZAN DOMINGUEZ', 'ELIAS', '953239923', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(109, '46155707', 'RODRIGUEZ VELASQUEZ', 'YOIMER ANTONIO', '949348694', 'BULDIBUYO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(110, '42494197', 'PONCE CABALLERO', 'TITO', '952328574', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(111, '02307112', 'COILA BELIZARIO', 'DANIEL ROMULO', '973781826', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(112, '98956544', 'PIZAN CHAVEZ', '', '', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(113, '19432589', 'SANTISTEBAN MENDOZA', 'JAVIER', '948087086', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(114, '41588687', 'PONTE CEVALLOS', 'CARLOS ENRIQUE', '948246074', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(115, '22961831', 'ARTURO', 'MODESTO ANDRES', '982186907', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(116, '36162111', 'RUBEN', 'RUBEN', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(117, '11111234', 'PEDRO', 'PEDRO', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(118, '41750604', 'MORILLO AGREDA', 'JULIO', '953710762', 'PARCO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(119, '42382329', 'ASCATE MENDIETA', 'LOYER', '948097803', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(120, '42525966', 'DIAZ BOLAÑOS', 'JOSUE LIZADO', '0', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(121, '45454541', 'ACUÑA DOMINGUEZ', 'DEMETRIO', '968329990', 'LLACABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(122, '26959921', 'COBRE', 'COBRE', '973038110', 'OTUZCO', 'M', 4, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(123, '71987664', 'LARA TATAQUILLA', 'JENSS', '983151853', 'TRUJILLO', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(124, '46634687', 'HENRIQUEZ CARRERA', 'WILSON', '944898939', 'TRUJILLO', 'M', 6, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(125, '47770087', 'CUEVAS MARTELL', 'MAI', '944104227', 'SARTINBAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(126, '71987648', 'INVERSIONES', 'LA SOLEDAD', '989459752', 'LA SOLEDAD', 'M', 7, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(127, '19418465', 'MENDIETA SAVEDRA DE VASQUES', 'LUZ', '986778669', 'PARCOY', 'F', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(128, '40329157', 'ROMAN CARCAMO', 'CARLOS ABERTO', '', 'TRUJILLO', 'M', 8, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(129, '32974335', 'CABALLERO QUIÑONES', 'LEONARDO', '962368643', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(130, '25416151', 'EMILIO MENDIETA', 'SILVA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(131, '44865501', 'PONTE ZEVALLOS', 'WUDILFREDO', '968744738', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(132, '8542133', 'TACO', 'TACO', '992309893', 'PARCO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(133, '19524637', 'NEGREIROS TENA', 'JESUS', '947727212', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(134, '99911111', 'DAVID', 'DAVID', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(135, '12121200', 'LEO', 'LEO', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(136, '22515115', 'HIDRO', 'HIDRO', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(137, '92251111', 'P NUEVO', 'P NUEVO', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(138, '87722223', 'TUCO', 'TUCO', '691561551', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(139, '21814100', 'ELMER', 'ELMER', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(140, '48597485', 'QUINTANA PEREA ', 'JHON', '997714097', 'TRUJILLO', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(141, '19099248', 'BERRIOS GONZALES', 'JOSE TEDDY', '', 'TRUJILLO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(142, '19425933', 'VILLANUEVA ZANCHEZ', 'ALBINO', '', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(143, '43713333', 'CORONEL CURO', 'MANUEL', '992123539', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(144, '82788652', 'VERA LUNA', 'VICTORIA', '973068837', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(145, '48418818', 'GUTIERRES ALEJANDO', 'CESAR ALEJANDRO', '', '', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(146, '8765432', 'MINA HUACRACHUCO', 'COBRE', '955674424', 'HUACRACUCO', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(147, '47556802', 'REY NAVARRO', 'ALEX', '952323479', 'LAS LOMAS', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(148, '78544444', 'REY NAVARRO', 'JHONNY', '937531733', 'LAS LOMAS', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(149, '89655454', 'OLORTINO BARRIOS', 'LUIS', '454444', 'CHIMBOTE', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(150, '43574503', 'LOZANO FLORES', 'NIKIOLI JONATAN', '973384367', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(151, '000137', 'DECORACIONES SEBASTIAN', 'HNOS APONTE', '949266670', 'AV. GRAN CHIMU #1638 LA ESPERANZA', 'M', 6, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(152, '11000041', 'MARITIN', 'MARTIN', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(153, '91124111', 'IPARRAGUIRRE', 'EDWAR', '', 'TRUJILLO', 'M', 7, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(154, '18094984', 'RODRIGUEZ RUIZ', 'WILLAM', '968728263', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(155, '45152315', 'RODRIGUEZ VERGARA', 'PEDRO', '939422502', 'MOCHE', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(156, '72377343', 'ROMERO SOTO', 'AMILCAR AARON', '977172244', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(157, '42937168', 'CONTRERAS VARGAS', 'ELMER', '', '', 'M', 7, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(158, '40219185', 'SILVA PAZ', 'MARIA ESTHER', '940263051', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(159, '44221680', 'VILLANUEVA TORREALVA', 'DANILO', '961892056', 'LA SOLEDAD', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(160, '43249067', 'NAVEZ ROSALES', 'PEDRO', '978388241', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(161, '32734048', 'VALTAZAR AVILA', 'JOSE', '943346558', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(162, '44141967', 'VILLANUEVA NORIEGA', 'REYNER', '939246634', 'TRUJILLO', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(163, '80014576', 'MINA', 'JUAN', '983066186', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(164, '19432130', 'MORILLO GUILLEN', 'CLEMENTE ', '992064411', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(165, '46042661', 'VILLANUEVA NORIEGA', 'JORGE', '968733781', '', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(166, '14151515', 'PATIO', 'CASEDA DOMINGUES', '', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(167, '24082017', 'SAMUEL', 'SAMUEL ', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(168, '24182017', 'VALDEZ ', 'VALDEZ', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(169, '1114118', 'CASEDA DOMINGUEZ', 'SANCHEZ', '973764272', 'PATAZ', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(170, '42654465', 'SAVEEDRA VELEZMORO', 'JOSE', '14', 'CHIMBOTE', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(171, '43294866', 'VELARDE SEDANO', 'NAVAL KENEDY', '973765467', 'RETMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(172, '19435133', 'SANCHEZ RODRIGUEZ', 'CESAR', '964252482', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(173, '44221681', 'MINA ', 'LA SOLEDAD', '961892056', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(174, '19419434', 'SALDAÑA BEJARANO', 'LUCIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(175, '42762970', 'ROMERO GOMEZ', 'DAVID ERASMO', '943178292', 'QUIRUVILCA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(176, '45917489', 'BARRENA MEZA', 'MARLON', '986377756', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(177, '19415495', 'MORA ARANDA', 'YGNACIO EFRAIN', '978757307', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(178, '47612155', 'FLORES MENDOZA', 'MAICOL', '978607940', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(179, '08529481', 'VEGA ARQUINIGO', 'GELACIO', '956328694', 'QUIRUVILCA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(180, '32734202', 'IPARRAGUIRRE CORREA ', 'EDGARDO', '947820717', '', 'M', 8, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(181, '18215673', 'POMACONDOR GARCIA', 'MARCOS', '946869083', 'RETMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(182, '11121210', 'LARA TORRES', 'DIXON', '', 'PATAZ', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(183, '12000333', 'QUIÑONES', 'ROBERTO', '', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(184, '30000411', 'CALDERON', 'FELIX', '', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(185, '42621296', 'LOPEZ PEREZ', 'ELISABETH ROSMERI', '910129118', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(186, '19534562', 'MIRANDA VEGA ', 'FIDENCIA ', '953146264', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(187, '14092017', 'CARJABAL', 'CARBAJAL', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(188, '71445325', 'YONAR ', 'MAESTRITO', '7455665', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(189, '62223322', 'SANCHEZ', 'SANCHEZ', '551213321', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(190, '54236871', 'LUIS', 'LUIS', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(191, '19435140', 'VALDIVIEZO MENDOZA', 'ALEJANDRO', '951591320', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(192, '46856206', 'CRUZ ANAMPA', 'EDGAR', '983054764', 'QUIRUVILCA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(193, '01010101', 'JYJ', 'JYJ', '044503840', 'JR. CRISTOBAL LZANO 831 URB. EL BOSQUE', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(194, '45227855', 'PLASENCIA CRUZ ', 'JHONY', '982821471', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(195, '15252225', 'TRINIDAD MARREROS', 'CARLOS', '', '', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(196, '26959923', 'CORDOVA RODRIGUES', 'HENRY ESTIVEN', '973038110', 'MZ:13 LT:60 LOS GRANITOS LA RINCONADA - TRUJILLO', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(197, '72626335', 'PEREZ ALAYO', 'JAVIER', '971787784', 'QUIRUVILCA', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(198, '42465799', 'NEYRA RIOJA', 'GEANCARLO', '921365455', 'QUIRUVILCA', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(199, '19420922', 'RUFINO PEREDA', 'JUAN', '959226669', 'PATAZ', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(200, '17102017', 'MINA OTUZCO', 'COBRE', '', '', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(201, '23102017', 'MINA CHILIA', 'CHILIA', '', '', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(202, '44303293', 'TERRONES FLORES', 'MARIO', '994847775', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(203, '26102017', 'EUSEBIO', 'RAMIEREZ', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(204, '19431561', 'HENRIQUEZ RODRIGUEZ', 'DIONCIO', '992670449', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(205, '45098606', 'AGUILAR QUEZADA', 'ISMELDO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(206, '48245294', 'VELASQUEZ VERA', 'CASEMIRO', '943582128', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(207, '31102017', 'VIDAL TORRES', 'LUIS', '', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(208, '06112017', 'LOPEZ TUMBAJULCA', 'FEDERICO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(209, '43421016', 'SILVA CURODA ', 'RENE', '987527380', 'LLACUABAMBA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(210, '40685948', 'DOMINGUES HENRRIQUEZ', 'FAUSTO', '944070201', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(211, '73889985', 'ARELLANO FERNANDES', 'DEYMAR', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(212, '19434266', 'SEVILLANO HERRRERA', 'MARINO', '978693239', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(213, '09112017', 'DEYBIS', 'GASHAY', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(214, '42202990', 'PUMACHAICO MESA', 'JORGE', '949729188', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(215, '46903747', 'CARRASCO ALMASAN', 'DEYBIS GASHAY', '978781433', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(216, '89858654', 'EDGAR', 'EDGAR', '785944', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(217, '7125645', 'ELIAS', 'ELIAS', '2141', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(218, '42674625', 'CABREAR VILCHEZ', 'JUAN CARLOS', '967250784', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(219, '19433000', 'MARREROS SILVA', 'ROGER', '973741975', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(220, '29112017', 'BALTAZAR', 'JOSE', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(221, '73617247', 'TORRES VASQUES', 'ROGLER PIRI', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(222, '47336264', 'PIZAN DOMINGUEZ', 'RICARDO', '953260350', 'PARCOY', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(223, '42950483', 'LEDESMA PAREDES', 'OMIRIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(224, '18905542', 'AGUILAR ASAÑERO', 'SEGUNDO ARTEMIO', '986334388', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(225, '10578247', 'ZAMORA ZAMORA', 'MARCELO', '953188905', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(226, '13122017', 'SEVILLANO PAZ', 'SANTOS', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(227, '19431637', 'MORENO OTINIANO', 'ORACIO', '983084966', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(228, '18122011', 'MATOS', 'RAUL', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(229, '75845645', 'HONORIO RIOS ', 'CARLOS', '951551086', 'RATAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(230, '7897466', 'GAMEZ ', 'EUSEBIO', '545646464', 'PATAZ', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(231, '08012018', 'QUIÑONES', 'REYNALDO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(232, '80014577', 'DOMINGUEZ GUTARRA', 'JUAN OSCAR', '983066186', '', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(233, '13012018', 'CAMPOS', 'MARTIN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(234, '03333333', 'COMPRA COBRE', 'PIURA', '952323479', 'PIURA', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(235, '17012018', 'MALDONADO', 'JAVIER', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(236, '17012019', 'MOYA', 'MOYA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(237, '18012018', 'CARRUITERO GOICOCHEA', 'OSCAR LUIS', '932004395', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(238, '00000005', 'MINA', 'CASCAS', '95612335', 'CASCAS', 'M', 3, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(239, '01022018', 'JAVIER', 'CALDERON GUTIERRES', '972206220', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(240, '46620659', 'MEZA GUILLEN', 'MODESTO', '972318118', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(241, '42394050', 'DOMINGUEZ HENRRIQUEZ', 'ARMANDO', '953066813', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(242, '44779309', 'PEREZ RODRIGUEZ ', 'GONZALO', '939331466', 'TRUJILLO', 'M', 6, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(243, '45483787', 'PALACIOS ROMERO', 'NELSON ERASMO', '968073394', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(244, '19691776', 'NELENDES CONTRERAS', 'ORLANDO', '950777709', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(245, '22022018', 'GARCIA', 'LUIS', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(246, '34463530', 'DOMINGUEZ DOMINGUEZ', 'ELVIS', '932537220', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(247, '48094305', 'HUACANHULCA VILLAVUEVA', 'JOSE DIONICIO', '963047354', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(248, '12032018', 'PONTE ROJAS', 'AURELIANO ISIDRO', '943947764', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(249, '14032018', 'CADILLO GONZALES', 'SUSAN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(250, '42002951', 'LOPEZ PEREZ', 'LUIS ALBERTO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(251, '17032018', 'BAILON VERA', 'ANDRES', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(252, '19432478', 'MENDOZA VALIDIVIESO', 'ROMAN', '990013806', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(253, '21032018', 'PONTE', 'PERCILES', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(254, '20032018', 'MINA QUEBRADA', 'HUACRACHUCRO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(255, '18155009', 'SOTO ASPIROS', 'DESIDERIO', '978330473', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(256, '19407815', 'ARELLANO LOPEZ', 'MODESTO', '940335122', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(257, '40367441', 'VIERA USQUIANO', 'TERESA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(258, '29629238', 'CALLO GONZALES', 'SUSANA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(259, '03042018', 'MILTION', 'MILTON', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(260, '23042018', 'SEVILLANO TICLO', 'JUAN', '', '', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(261, '09743104', 'LESCANO CARRION', 'CARLOS CESAR', '951925953', 'TRUJILLO', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(262, '40840790', 'HERRERA MENDOZA', 'LUCIO', '972706479', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(263, '23263361', 'ACUÑA MAYGUA', 'CESAR', '991745841', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(264, '19400898', 'MALDONADO ABURTO', 'ANGEL', '944137468', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(265, '47159846', 'RAMIREZ', 'ERIK ROBIN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(266, '01189465', 'ALTAMIRANO SALINAS', 'SANTOS', '957299771', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(267, '45828674', 'QUIÑONES PUMACHAICO', 'MAXIMO', '973494920', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(268, '47104822', 'FERNANDES SOBERON', 'MANUEL', '964353308', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(269, '19426317', 'BOCANEGRA OLORTEGUI', 'EDAR EVERTH', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(270, '61821849', 'RIOS HONORIO', 'WILDER', '967069051', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(271, '46470585', 'PERALTA TORRES ', 'EDWIN', '992665003', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(272, '41223561', 'ARELLANO SEVILLANO', 'YENI', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(273, '24172018', 'MACHUCA', 'JAIRO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(274, '19415808', 'SILVESTRE VELASQUEZ', 'TRUJILLO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(275, '40735382', 'BALDION GANBOA', 'EDGAR', '992196994', 'LIMA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(276, '11111111', 'RAMIREZ', 'EUSEBIO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(277, '72375543', 'HUACHO HERRERA', 'SAMUDIO', '940930598', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(278, '20081598', 'LLANTOS CERRON', 'WILDER', '960047715', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(279, '48289750', 'PIZAN CRESPIN', 'JEAN PIER', '973404876', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(280, '08082018', 'CORNELIO', 'MILLER', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(281, '43594000', 'FARJE RODRIGUEZ', 'KENY', '997676066', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(282, '43694164', 'LEYVA MARIÑOS', 'EVER', '913371366', 'ABCDE', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(283, '01092018', 'SAVEDRA ', 'JOSE', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(284, '06092018', 'GORDILLO', 'WILDER', '', '', 'M', 8, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(285, '11221112', 'MENDOZA', 'DOCTOR', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(286, '11515511', 'EDER RODRIGUEZ', 'MINERAL VIJUS', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(287, '43908012', 'FERNANDO SEVILLANO', 'PAZ', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(288, '15092018', 'CASTILLO DOMINGUEZ', 'MARIA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(289, '20092018', 'CHACON  RODRIGUES', 'NICOLAS', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(290, '42134678', 'CACEDA DOMINGUEZ', 'TITO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(291, '47425515', 'INGA CAMPOS ', 'SUMNER', '', '', 'M', 5, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(292, '21092018', 'MELIN', 'MELIN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(293, '26092018', 'MERCEDES ', 'CISNEROS', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(294, '40840784', 'HENRIQUEZ VIGO', 'RUBEN', '972312555', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(295, '11102018', 'CHAVEZ', 'EDMAN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(296, '42876219', 'HENRIQUEZ VIGO', 'FREDY', '974331537', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(297, '72577177', 'AVILA ROSALES', 'JAIRO', '961729601', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(298, '24102018', 'CIUDAD', 'JUAN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(299, '44417245', 'MARTINES PEÑA', 'MAITER', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(300, '46491471', 'VASQUEZ CORNELIO', 'DICLA', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(301, '12112018', 'CONCENTRADO', 'POLIMETALICO', '', '', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(302, '12121212', 'LEIVA MARIÑOS', 'JAVIER', '99999999', 'RETAMAS', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(303, '40698471', 'HUANCA CALCINA', 'EDDY', '996602627', 'TRUJILLO', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(304, '22112018', 'DOMINGUEZ HENRIQUEZ', 'YOLA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(305, '19562748', 'ALEGRIA LOYOLA', 'FORTUNATO', '963197316', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(306, '45897327', 'ALVARADO MORENO ', 'JUAN', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(307, '18122018', 'MINA ALBORADA', 'TAYABAMBA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(308, '47534924', 'VASQUEZ MENDIETA', 'ALDIMER', '983256475', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(309, '46455368', 'CHAMPE LOAYZA', 'JHONY', '971366779', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(310, '27909188', 'QUIROS IZQUIERDO', 'JUAN', '', 'QUIRUVILCA', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(311, '92368121', 'MODESTO HERRERA', 'BRAYAN', '953888426', 'LA SOLEDAD', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(312, '3001219', 'GONZALO GUTIERRES', 'MARIO GUALBERTO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(313, '06564963', 'LOZANO CORONEL ', 'FRANCISCO', '', 'PATAZ', 'M', 2, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(314, '43330533', 'FAUSITINO CAMPOS', 'ANGEL', '953894180', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(315, '18022019', 'MEJIA RODRIGUEZ', 'ELMER', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(316, '47262427', 'HENRIQUEZ VIGO', 'MERARDO', '974830003', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(317, '47952653', 'CHESIO MENDOZA', 'JUAN', '926950598', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(318, '19434821', 'SEVILLANO PAZ', 'SANTOS AGUSTO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(319, '43640561', 'CHAMPE LOAYZA', 'FIDEL', '928611713', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(320, '43390071', 'CAMPOS', 'HUMBERTO', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(321, '62720274', 'LARA TORRES', 'YANA', '', '', 'M', 1, 1, '2019-04-01 22:34:54', NULL, NULL, 31, 1),
(322, '73531482', 'VELASQUEZ PAZ', 'LEVI', '983332468', 'TRUJILLO', 'M', 1, 3, '2019-05-18 19:30:12', NULL, NULL, 31, 1);

-- --------------------------------------------------------

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
CREATE TABLE IF NOT EXISTS `compra` (
  `Compra_Id` int(11) NOT NULL AUTO_INCREMENT,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `ClienteEntrante_Id` int(11) NOT NULL,
  `Rendicion_Id` int(11) NOT NULL,
  `Compra_Sacos` decimal(9,0) NOT NULL,
  `Compra_Peso` decimal(9,0) NOT NULL,
  `Compra_Ley` varchar(100) NOT NULL,
  `Compra_Precio` decimal(9,0) NOT NULL,
  `Compra_Total` decimal(9,0) NOT NULL,
  `Compra_Gastos` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `Compra_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Compra_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Compra_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`Compra_Id`),
  KEY `asdasd` (`Estado_Id`),
  KEY `aassdasd` (`ProveedorServicio_Id`),
  KEY `aassdasasd` (`ClienteEntrante_Id`),
  KEY `asas` (`Rendicion_Id`),
  KEY `Personal_Id` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `concepto`
--

DROP TABLE IF EXISTS `concepto`;
CREATE TABLE IF NOT EXISTS `concepto` (
  `Concepto_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Concepto_Descripcion` varchar(80) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Concepto_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Concepto_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Concepto_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`Concepto_Id`),
  UNIQUE KEY `Concepto_Descripcion` (`Concepto_Descripcion`),
  KEY `asdasd` (`Estado_Id`),
  KEY `Personal_Id` (`Personal_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `concepto`
--

INSERT INTO `concepto` (`Concepto_Id`, `Concepto_Descripcion`, `Estado_Id`, `Concepto_Fecha_Registrado`, `Concepto_Fecha_Actualizado`, `Concepto_Fecha_Eliminado`, `Personal_Id`) VALUES
(1, 'ADELANTO', 1, '2019-03-08 19:56:48', NULL, NULL, 31),
(2, 'PASAJES', 1, '2019-04-06 17:01:42', NULL, NULL, 31);

-- --------------------------------------------------------

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
CREATE TABLE IF NOT EXISTS `contrato` (
  `Contrato_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Personal_Id` int(11) NOT NULL,
  `Contrato_FechaContrato` date NOT NULL,
  `Contrato_FechaFin` date NOT NULL,
  `Contrato_TotalDiasPagar` int(11) NOT NULL,
  `Contrato_Sueldo` decimal(9,2) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '4',
  `Empresa_Id` int(11) NOT NULL,
  PRIMARY KEY (`Contrato_Id`),
  KEY `sadasdaaaa` (`Empresa_Id`),
  KEY `sadasdaaaaaa` (`Estado_Id`),
  KEY `sadasdaaaaggaa` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `correo_factura`
--

DROP TABLE IF EXISTS `correo_factura`;
CREATE TABLE IF NOT EXISTS `correo_factura` (
  `CorreFactura_Id` int(11) NOT NULL AUTO_INCREMENT,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `CorreoFactura_Descripcion` varchar(500) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `FacturaElectronica_Id` int(11) NOT NULL,
  PRIMARY KEY (`CorreFactura_Id`),
  KEY `asdasdasdf` (`ProveedorServicio_Id`),
  KEY `asdasdaasdasdsdf` (`Estado_Id`),
  KEY `asdadasdasd` (`FacturaElectronica_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detalle_permiso_personal`
--

DROP TABLE IF EXISTS `detalle_permiso_personal`;
CREATE TABLE IF NOT EXISTS `detalle_permiso_personal` (
  `DetallePermisoPersonal_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Personal_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '2',
  `Empresa_Id` int(11) NOT NULL,
  `Permisos_Id` int(11) NOT NULL,
  PRIMARY KEY (`DetallePermisoPersonal_Id`),
  KEY `sssss` (`Empresa_Id`),
  KEY `sssaass` (`Estado_Id`),
  KEY `fdddddd` (`Personal_Id`),
  KEY `fdddddaaad` (`Permisos_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalle_permiso_personal`
--

INSERT INTO `detalle_permiso_personal` (`DetallePermisoPersonal_Id`, `Personal_Id`, `Estado_Id`, `Empresa_Id`, `Permisos_Id`) VALUES
(54, 31, 1, 1, 1),
(55, 31, 1, 1, 2),
(56, 31, 1, 1, 3),
(57, 31, 1, 1, 4),
(58, 31, 1, 1, 5),
(59, 31, 1, 1, 6),
(60, 31, 1, 1, 7),
(61, 31, 1, 1, 8),
(62, 31, 1, 1, 9),
(63, 31, 1, 1, 10),
(64, 31, 1, 1, 11),
(75, 35, 2, 1, 1),
(76, 35, 2, 1, 2),
(77, 35, 2, 1, 3),
(78, 35, 2, 1, 4),
(79, 35, 2, 1, 5),
(80, 35, 2, 1, 6),
(81, 35, 2, 1, 7),
(82, 35, 2, 1, 8),
(83, 35, 2, 1, 9),
(84, 35, 2, 1, 10),
(85, 35, 1, 1, 11),
(86, 36, 2, 1, 1),
(87, 36, 2, 1, 2),
(88, 36, 2, 1, 3),
(89, 36, 2, 1, 4),
(90, 36, 2, 1, 5),
(91, 36, 2, 1, 6),
(92, 36, 2, 1, 7),
(93, 36, 2, 1, 8),
(94, 36, 2, 1, 9),
(95, 36, 2, 1, 10),
(96, 36, 2, 1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `Empresa_id` int(11) NOT NULL AUTO_INCREMENT,
  `Empresa_Departamento` varchar(80) NOT NULL,
  `Empresa_Direccion` varchar(80) NOT NULL,
  `Empresa_Nombre_Comercial` varchar(80) NOT NULL,
  `Empresa_Distrito` varchar(80) NOT NULL,
  `Empresa_Nombre_Legal` varchar(80) NOT NULL,
  `Empresa_Nro_Documento` varchar(50) NOT NULL,
  `Empresa_Tipo_Documento` varchar(5) NOT NULL,
  `Empresa_Ubigeo` varchar(50) NOT NULL,
  `Empresa_Urbanizacion` varchar(50) NOT NULL,
  `Empresa_Provincia` varchar(80) NOT NULL,
  `Empresa_RutaPFX` varchar(1024) NOT NULL,
  `Empresa_ClavePFX` varchar(126) NOT NULL,
  `Empresa_RutaXML` varchar(1024) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `Empresa_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Empresa_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Empresa_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Empresa_Mision` varchar(2000) NOT NULL,
  `Empresa_Vision` varchar(2000) NOT NULL,
  `Empresa_Telefono` char(9) NOT NULL,
  `Empresa_RutaIImagen` varchar(1024) NOT NULL,
  `Empresa_CodigoLocal` varchar(20) NOT NULL,
  `Empresa_NombreBD` varchar(50) NOT NULL,
  `Empresa_UsuarioSunat` varchar(80) NOT NULL,
  `Empresa_PasswordSunat` varchar(80) NOT NULL,
  `Empresa_EndPointUrl` varchar(500) NOT NULL,
  `Empresa_signature` varchar(50) NOT NULL,
  `Empresa_RutaXMLNotaDebito` varchar(1024) NOT NULL,
  `Empresa_RutaXMLNotaCredito` varchar(1024) NOT NULL,
  PRIMARY KEY (`Empresa_id`),
  KEY `asdasd` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empresa`
--

INSERT INTO `empresa` (`Empresa_id`, `Empresa_Departamento`, `Empresa_Direccion`, `Empresa_Nombre_Comercial`, `Empresa_Distrito`, `Empresa_Nombre_Legal`, `Empresa_Nro_Documento`, `Empresa_Tipo_Documento`, `Empresa_Ubigeo`, `Empresa_Urbanizacion`, `Empresa_Provincia`, `Empresa_RutaPFX`, `Empresa_ClavePFX`, `Empresa_RutaXML`, `Estado_Id`, `Empresa_Fecha_Registrado`, `Empresa_Fecha_Actualizado`, `Empresa_Fecha_Eliminado`, `Empresa_Mision`, `Empresa_Vision`, `Empresa_Telefono`, `Empresa_RutaIImagen`, `Empresa_CodigoLocal`, `Empresa_NombreBD`, `Empresa_UsuarioSunat`, `Empresa_PasswordSunat`, `Empresa_EndPointUrl`, `Empresa_signature`, `Empresa_RutaXMLNotaDebito`, `Empresa_RutaXMLNotaCredito`) VALUES
(1, 'LIMA', 'CAL. ANTENOR ORREGO 2579 URB. CONDEVILLA SEÑOR Y VALDIVIESO ET. DOS SC. UNO', 'YERSICA DANIS LARA TANTAQUILLA', 'SAN MARTIN DE PORREZ', 'YERSICA DANIS LARA TANTAQUILLA', '10719876612', '6', '0', '1', 'LIMA', 'C:\\FACTURA ELECTRONICA\\YERSICA\\CERTIFICADO\\C1902205383.pfx', '10719876612', 'C:\\FACTURA ELECTRONICA\\YERSICA\\ARCHIV_XML\\', 1, '2019-02-12 19:03:49', NULL, NULL, 'Producir concentrados minerales y metales, garantizando la creación de valor para los accionistas. Realizar actividades de exploración, asegurando la continuidad del proceso de explotación del mineral, generando oportunidades de desarrollo para nuestros colaboradores y las comunidades del entorno. Mantener el compromiso de operar y desarrollar nuestros proyectos con innovación, eficacia, seguridad, responsabilidad social y ambiental y buen gobierno corporativo.', 'El Brocal es una empresa minero metalúrgica moderna, que opera con rentabilidad en sus inversiones; cuenta con amplios recursos y reservas de mineral que garantizan su sostenibilidad y crecimiento en el mediano y largo plazo, en base a nuevas operaciones mineras que opera con responsabilidad para con su entorno.', '983332468', '/SistemaLara/capa5_imagenes/LogoYe.png', '0001', 'bdsistemayersica', 'BRUCELEE', 'Danylee313', 'https://e-factura.sunat.gob.pe:443/ol-ti-itcpfegem/billService', 'LARA', 'C:\\FACTURA ELECTRONICA\\YERSICA\\ARCHIV_XMLNotaDebito\\', 'C:\\FACTURA ELECTRONICA\\YERSICA\\ARCHIV_XMLNotaCredito\\');

-- --------------------------------------------------------

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
  `Estado_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Estado_Descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estado`
--

INSERT INTO `estado` (`Estado_Id`, `Estado_Descripcion`) VALUES
(1, 'Activo'),
(2, 'Inactivo'),
(3, 'Eliminado'),
(4, 'Contrato Activo'),
(5, 'Contrato Inactivo'),
(6, 'Contrato Eliminado'),
(7, 'Adelanto Pagado'),
(8, 'Adelanto Por Pagar'),
(9, 'Adelanto Eliminado'),
(10, 'Pago Pagado'),
(11, 'Pago Eliminado'),
(12, 'Por Hacer'),
(13, 'Hechas'),
(14, 'REINTEGRO SI'),
(15, 'REINTEGRO NO');

-- --------------------------------------------------------

--
-- Table structure for table `estado_factura_electronica`
--

DROP TABLE IF EXISTS `estado_factura_electronica`;
CREATE TABLE IF NOT EXISTS `estado_factura_electronica` (
  `EstadoFacturaElectronica_Id` int(11) NOT NULL AUTO_INCREMENT,
  `EstadoFacturaElectronica_Descripcion` varchar(30) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  PRIMARY KEY (`EstadoFacturaElectronica_Id`),
  KEY `fjgjgjgjgjgjjg` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estado_factura_electronica`
--

INSERT INTO `estado_factura_electronica` (`EstadoFacturaElectronica_Id`, `EstadoFacturaElectronica_Descripcion`, `Estado_Id`) VALUES
(1, 'SIN ENVIAR', 1),
(2, 'DE BAJA', 1),
(3, 'EMITIDO', 1);

-- --------------------------------------------------------

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE IF NOT EXISTS `factura` (
  `Factura_Id` int(11) NOT NULL,
  `Factura_Nro` varchar(50) NOT NULL,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `Factura_Fecha` date NOT NULL,
  `Factura_Guian` char(50) NOT NULL,
  `Factura_Direccion` varchar(200) NOT NULL,
  `Factura_ValorVenta` decimal(9,2) DEFAULT NULL,
  `Factura_IGV` decimal(9,2) DEFAULT NULL,
  `Factura_Total` decimal(9,2) DEFAULT NULL,
  `Factura_Descripcion` varchar(50) NOT NULL,
  `Factura_Moneda` char(5) NOT NULL,
  `Factura_CodigoUnico` char(250) NOT NULL,
  `Factura_RNC` char(30) NOT NULL,
  `Factura_Lectura` varchar(500) NOT NULL,
  `Factura_Estado` varchar(50) NOT NULL DEFAULT 'Activado',
  `Factura_FechaPago` date DEFAULT NULL,
  `Empresa_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Factura_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Factura_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Factura_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`Factura_Id`),
  UNIQUE KEY `Factura_Nro` (`Factura_Nro`),
  UNIQUE KEY `Factura_Nro_2` (`Factura_Nro`),
  KEY `asdasdasd` (`Personal_Id`),
  KEY `asdasasdasddasd` (`Empresa_Id`),
  KEY `asdasasdadasd` (`ProveedorServicio_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `factura`
--
DROP TRIGGER IF EXISTS `FacturaElectronica_Agregar_sp`;
DELIMITER $$
CREATE TRIGGER `FacturaElectronica_Agregar_sp` AFTER INSERT ON `factura` FOR EACH ROW INSERT INTO factura_electronica(Factura_Id) VALUES(new.Factura_Id)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `factura_detalle`
--

DROP TABLE IF EXISTS `factura_detalle`;
CREATE TABLE IF NOT EXISTS `factura_detalle` (
  `FacturaDetalle_Id` int(11) NOT NULL AUTO_INCREMENT,
  `FacturaDetalle_Cantidad` varchar(20) NOT NULL,
  `FacturaDetalle_Unidad` char(50) NOT NULL,
  `FacturaDetalle_Descripcion` varchar(200) NOT NULL,
  `FacturaDetalle_Precio_Unitario` decimal(9,2) DEFAULT NULL,
  `FacturaDetalle_Importe` char(50) NOT NULL,
  `Factura_Id` int(11) NOT NULL,
  `FacturaDetalle_Adelanto` varchar(20) NOT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `FacturaDetalle_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `FacturaDetalle_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `FacturaDetalle_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Liquidacion_Id` int(11) NOT NULL,
  PRIMARY KEY (`FacturaDetalle_Id`),
  KEY `asdallsd` (`Liquidacion_Id`),
  KEY `asdasdaaaasd` (`Estado_Id`),
  KEY `asdasdasgfgaaasd` (`Factura_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `factura_electronica`
--

DROP TABLE IF EXISTS `factura_electronica`;
CREATE TABLE IF NOT EXISTS `factura_electronica` (
  `FacturaElectronica_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Factura_Id` int(11) NOT NULL,
  `FacturaElectronica_RutaXML` varchar(1000) DEFAULT NULL,
  `FacturaElectronica_RutaPDF` varchar(1024) DEFAULT NULL,
  `FacturaElectronica_FechaGeneracionArchivo` varchar(100) DEFAULT NULL,
  `FacturaElectronica_RutaGeneracionBaja` varchar(1024) DEFAULT NULL,
  `EstadoFacturaElectronica_Id` int(11) NOT NULL DEFAULT '1',
  `FacturaElectronica_FechaDeBaja` varchar(100) DEFAULT NULL,
  `FacturaElectronica_RutaXMLBaja` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`FacturaElectronica_Id`),
  KEY `asdasdasdasda` (`Factura_Id`),
  KEY `asdasdasdasssda` (`EstadoFacturaElectronica_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `factura_numero`
--

DROP TABLE IF EXISTS `factura_numero`;
CREATE TABLE IF NOT EXISTS `factura_numero` (
  `FacturaNumero_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Empresa_Id` int(11) NOT NULL,
  `FacturaNumero_Serio` varchar(20) NOT NULL,
  `FacturaNumero_Correlativo` int(11) NOT NULL,
  `FacturaNumero_Descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`FacturaNumero_Id`),
  KEY `asda112sd` (`Empresa_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `factura_numero`
--

INSERT INTO `factura_numero` (`FacturaNumero_Id`, `Empresa_Id`, `FacturaNumero_Serio`, `FacturaNumero_Correlativo`, `FacturaNumero_Descripcion`) VALUES
(3, 1, 'F001', 1, 'FACTURA'),
(4, 1, 'F001', 1, 'NOTA DEBITO'),
(5, 1, 'F001', 1, 'NOTA CREDITO');

-- --------------------------------------------------------

--
-- Table structure for table `gastos_extras`
--

DROP TABLE IF EXISTS `gastos_extras`;
CREATE TABLE IF NOT EXISTS `gastos_extras` (
  `GastosExtras_Id` int(11) NOT NULL AUTO_INCREMENT,
  `GastosExtras_Descripcion` varchar(250) NOT NULL,
  `GastosExtras_Monto` decimal(9,2) DEFAULT NULL,
  `ClienteEntrante_Id` int(11) NOT NULL,
  `Concepto_Id` int(11) NOT NULL,
  `GastosExtras_Moneda` varchar(5) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `GastosExtras_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `GastosExtras_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `GastosExtras_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`GastosExtras_Id`),
  KEY `asdasdasff` (`Concepto_Id`),
  KEY `asdasd11asff` (`Estado_Id`),
  KEY `asdaAAsd11asff` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `liquidacion`
--

DROP TABLE IF EXISTS `liquidacion`;
CREATE TABLE IF NOT EXISTS `liquidacion` (
  `Liquidacion_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Liquidacion_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Liquidacion_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Liquidacion_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Procedencia_Id` int(11) NOT NULL,
  `Liquidacion_Fecha` date NOT NULL,
  `ClienteEntrante_Id` int(11) NOT NULL,
  `Liquidacion_Lote` int(20) NOT NULL,
  `Liquidacion_Tmh` text NOT NULL,
  `Liquidacion_H2O` text NOT NULL,
  `Liquidacion_Tms` text NOT NULL,
  `Liquidacion_Leyau` text NOT NULL,
  `Liquidacion_Leyag` text NOT NULL,
  `Liquidacion_Inter` text NOT NULL,
  `Liquidacion_Rec` text NOT NULL,
  `Liquidacion_Maquilla` text NOT NULL,
  `Liquidacion_Factor` text NOT NULL,
  `Liquidacion_Conscon` text NOT NULL,
  `Liquidacion_Escalador` text NOT NULL,
  `Liquidacion_Stms` text NOT NULL,
  `Liquidacion_Totalus` text NOT NULL,
  `Liquidacion_Reintegro` text NOT NULL,
  `Liquidacion_Facturado` text,
  `Personal_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Liquidacion_Estado` text NOT NULL,
  `Empresa_Id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Liquidacion_Id`),
  KEY `asasdasddas` (`Procedencia_Id`),
  KEY `asdaasdasdsd` (`Estado_Id`),
  KEY `asdaasdaasdsdsd` (`ClienteEntrante_Id`),
  KEY `asooodaasdsdsd` (`Personal_Id`),
  KEY `ASDASDAS` (`Empresa_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
CREATE TABLE IF NOT EXISTS `log` (
  `log` varchar(1000) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`log`, `id`) VALUES
('no enviado por gacturacion', 48),
('no enviado por gacturacion', 47),
('no enviado por gacturacion', 46),
('no enviado por gacturacion', 45),
('no enviado por gacturacion', 44),
('no enviado por gacturacion', 43),
('no enviado por gacturacion', 42),
('no enviado por gacturacion', 41),
('no enviado por gacturacion', 40),
('no enviado por gacturacion', 39),
('no enviado por base de datrows', 38),
('no enviado por base de datrows', 26),
('no enviado por base de datrows nota credito', 27),
('no enviado por base de datrows nota credito', 28),
('no enviado por base de datrows nota credito', 29),
('no enviado por base de datrows', 30),
('no enviado por base de datrows', 31),
('no enviado por base de datrows nota credito', 32),
('no enviado por base de datrows nota credito', 33),
('no enviado por base de datrows', 34),
('no enviado por gacturacion', 35),
('no enviado por base de datrows nota credito', 36),
('no enviado por base de datrows nota credito', 37);

-- --------------------------------------------------------

--
-- Table structure for table `notacredito`
--

DROP TABLE IF EXISTS `notacredito`;
CREATE TABLE IF NOT EXISTS `notacredito` (
  `NotaCredito_id` int(11) NOT NULL AUTO_INCREMENT,
  `NotaCredito_NumeroNotaCreadito` char(50) NOT NULL,
  `NotaCredito_NumeroFactura` char(50) NOT NULL,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `NotaCredito_Denominacion` varchar(80) NOT NULL,
  `NotaCredito_FechaEmision` date NOT NULL,
  `NotaCredito_FechaEmision_Comprobante` date NOT NULL,
  `NotaCredito_SubtTotal` decimal(9,2) NOT NULL,
  `NotaCredito_IGV` decimal(9,2) NOT NULL,
  `NotaCredito_Total` decimal(9,2) NOT NULL,
  `NotaCredito_Moneda` char(5) NOT NULL,
  `NotaCredito_Lectura` varchar(500) NOT NULL,
  `NotaCredito_Fecha_Registrado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NotaCredito_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `NotaCredito_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Empresa_Id` int(11) NOT NULL DEFAULT '1',
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `NotaCredito_DescripcionMotivo` varchar(250) NOT NULL,
  `TipoNotaCredito_Id` int(11) DEFAULT '1',
  PRIMARY KEY (`NotaCredito_id`),
  UNIQUE KEY `NotaCredito_NroNotaCredito` (`NotaCredito_NumeroNotaCreadito`) USING BTREE,
  KEY `adsa` (`Empresa_Id`),
  KEY `adsajkjhkkhk` (`Personal_Id`),
  KEY `adsajkbjghjjhkkhk` (`Estado_Id`),
  KEY `asdasdasdsaasdss` (`TipoNotaCredito_Id`),
  KEY `proverdorfg` (`ProveedorServicio_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notacredito`
--

INSERT INTO `notacredito` (`NotaCredito_id`, `NotaCredito_NumeroNotaCreadito`, `NotaCredito_NumeroFactura`, `ProveedorServicio_Id`, `NotaCredito_Denominacion`, `NotaCredito_FechaEmision`, `NotaCredito_FechaEmision_Comprobante`, `NotaCredito_SubtTotal`, `NotaCredito_IGV`, `NotaCredito_Total`, `NotaCredito_Moneda`, `NotaCredito_Lectura`, `NotaCredito_Fecha_Registrado`, `NotaCredito_Fecha_Actualizado`, `NotaCredito_Fecha_Eliminado`, `Personal_Id`, `Empresa_Id`, `Estado_Id`, `NotaCredito_DescripcionMotivo`, `TipoNotaCredito_Id`) VALUES
(1, 'F001-09999999', 'F001-09999999', 1, 'FACTURA', '2019-05-18', '2019-05-18', '1.00', '0.18', '1.18', '$', 'UNO CON 18/100  DOLARES AMERICANOS', '2019-05-18 21:05:16', NULL, NULL, 31, 1, 1, 'ANULACION DE LA OPERACIÓN', 1);

--
-- Triggers `notacredito`
--
DROP TRIGGER IF EXISTS `NotaCredito_Agregar_sp`;
DELIMITER $$
CREATE TRIGGER `NotaCredito_Agregar_sp` AFTER INSERT ON `notacredito` FOR EACH ROW INSERT INTO notacredito_electronica(NotaCredito_Id) VALUES(new.NotaCredito_Id)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `notacredito_detalle`
--

DROP TABLE IF EXISTS `notacredito_detalle`;
CREATE TABLE IF NOT EXISTS `notacredito_detalle` (
  `NotaCreditoDetalle_Id` int(11) NOT NULL AUTO_INCREMENT,
  `NotaCreditoDetalle_Descripcion` varchar(500) NOT NULL,
  `NotaCreditoDetalle_ValorVenta` decimal(9,2) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `NotaCredito_Id` int(11) NOT NULL,
  `Personal_Id` int(11) NOT NULL,
  `NotaCreditoDetalle_Cantidad` int(11) DEFAULT '1',
  `NotaCreditoDetalle_PrecioUnitario` decimal(9,2) DEFAULT '1.00',
  PRIMARY KEY (`NotaCreditoDetalle_Id`),
  KEY `asdasdaaaaa` (`NotaCredito_Id`),
  KEY `asdasdaffaaaa` (`Estado_Id`),
  KEY `asdasdaffdddaaaa` (`Personal_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notacredito_detalle`
--

INSERT INTO `notacredito_detalle` (`NotaCreditoDetalle_Id`, `NotaCreditoDetalle_Descripcion`, `NotaCreditoDetalle_ValorVenta`, `Estado_Id`, `NotaCredito_Id`, `Personal_Id`, `NotaCreditoDetalle_Cantidad`, `NotaCreditoDetalle_PrecioUnitario`) VALUES
(3, '22', '1.00', 1, 1, 31, 1, '1.00');

-- --------------------------------------------------------

--
-- Table structure for table `notacredito_electronica`
--

DROP TABLE IF EXISTS `notacredito_electronica`;
CREATE TABLE IF NOT EXISTS `notacredito_electronica` (
  `NotaCreditoElectronica_Id` int(11) NOT NULL AUTO_INCREMENT,
  `NotaCreditoElectronica_RutaXML` varchar(1000) DEFAULT NULL,
  `NotaCreditoElectronica_RutaPDF` varchar(1024) DEFAULT NULL,
  `NotaCreditoElectronica_FechaGeneracionArchivo` varchar(100) DEFAULT NULL,
  `EstadoNotaCredito_Ids` int(11) NOT NULL DEFAULT '1',
  `NotaCreditoElectronica_FechaBaja` varchar(100) DEFAULT NULL,
  `NotaCreditoElectronica_RutaXMLBaja` varchar(1000) DEFAULT NULL,
  `NotaCredito_Id` int(11) NOT NULL,
  `EstadoNotaCredito_Id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`NotaCreditoElectronica_Id`),
  KEY `asdasdasd` (`NotaCredito_Id`),
  KEY `sadsadasdssssas` (`EstadoNotaCredito_Ids`),
  KEY `sadasdasdasdasdasd` (`EstadoNotaCredito_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notacredito_electronica`
--

INSERT INTO `notacredito_electronica` (`NotaCreditoElectronica_Id`, `NotaCreditoElectronica_RutaXML`, `NotaCreditoElectronica_RutaPDF`, `NotaCreditoElectronica_FechaGeneracionArchivo`, `EstadoNotaCredito_Ids`, `NotaCreditoElectronica_FechaBaja`, `NotaCreditoElectronica_RutaXMLBaja`, `NotaCredito_Id`, `EstadoNotaCredito_Id`) VALUES
(3, NULL, NULL, NULL, 1, NULL, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `notacredito_numero`
--

DROP TABLE IF EXISTS `notacredito_numero`;
CREATE TABLE IF NOT EXISTS `notacredito_numero` (
  `NotaCreditoNumero_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Empresa_Id` int(11) NOT NULL,
  `NotaCreditoNumero_Serie` varchar(20) NOT NULL,
  `NotaCreditoNumero_Correlativo` int(11) NOT NULL,
  PRIMARY KEY (`NotaCreditoNumero_Id`),
  KEY `adsa` (`Empresa_Id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notadebitodetalle`
--

DROP TABLE IF EXISTS `notadebitodetalle`;
CREATE TABLE IF NOT EXISTS `notadebitodetalle` (
  `NotaDebitoDetalle_Id` int(11) NOT NULL AUTO_INCREMENT,
  `NotaDebitoDetalle_Cantidad` decimal(9,3) NOT NULL,
  `NotaDebitoDetalle_Unidad` char(50) NOT NULL,
  `NotaDebitoDetalle_Descripcion` varchar(250) NOT NULL,
  `NotaDebitoDetalle_PrecioUnitario` decimal(9,2) NOT NULL,
  `NotaDebitoDetalle_ValorVenta` decimal(9,2) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `NotaDebito_Id` int(11) NOT NULL,
  `Reintegro_Id` int(11) NOT NULL,
  PRIMARY KEY (`NotaDebitoDetalle_Id`),
  KEY `asdassssdas` (`Estado_Id`),
  KEY `notadebidovfkdfdfsdfs` (`NotaDebito_Id`),
  KEY `reintegrofk` (`Reintegro_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notadebito_electronica`
--

DROP TABLE IF EXISTS `notadebito_electronica`;
CREATE TABLE IF NOT EXISTS `notadebito_electronica` (
  `NotaDebitoElectronica_Id` int(11) NOT NULL AUTO_INCREMENT,
  `NotaDebitoElectronica_RutaXML` varchar(1000) DEFAULT NULL,
  `NotaDebitoElectronica_RutaPDF` varchar(1024) DEFAULT NULL,
  `NotaDebitoElectronica_FechaGeneracionArchivo` varchar(100) DEFAULT NULL,
  `EstadoNotaDebito_Id` int(11) NOT NULL DEFAULT '1',
  `NotaDebitoElectronica_FechaBaja` varchar(100) DEFAULT NULL,
  `NotaDebitoElectronica_RutaXMLBaja` varchar(1000) DEFAULT NULL,
  `NotaDebito_Id` int(11) NOT NULL,
  PRIMARY KEY (`NotaDebitoElectronica_Id`),
  KEY `sadasdasdasd` (`NotaDebito_Id`),
  KEY `sadasdasddsdasd` (`EstadoNotaDebito_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `notadebito_numero`
--

DROP TABLE IF EXISTS `notadebito_numero`;
CREATE TABLE IF NOT EXISTS `notadebito_numero` (
  `NotaDebitoNumero_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Empresa_Id` int(11) NOT NULL,
  `NotaDebitoNumero_Serie` varchar(20) NOT NULL,
  `NotaDebitoNumero_Correlativo` int(11) NOT NULL,
  PRIMARY KEY (`NotaDebitoNumero_Id`),
  KEY `asdasdasddasd` (`Empresa_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notadebito_numero`
--

INSERT INTO `notadebito_numero` (`NotaDebitoNumero_Id`, `Empresa_Id`, `NotaDebitoNumero_Serie`, `NotaDebitoNumero_Correlativo`) VALUES
(1, 1, 'F001', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nota_debito`
--

DROP TABLE IF EXISTS `nota_debito`;
CREATE TABLE IF NOT EXISTS `nota_debito` (
  `NotaDebito_Id` int(11) NOT NULL AUTO_INCREMENT,
  `NotaDebito_NumeroNotaDebito` char(50) NOT NULL,
  `NotaDebito_NumeroFactura` char(50) NOT NULL,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `NotaDebito_Denominacion` varchar(80) NOT NULL,
  `NotaDebito_FechaEmision` date NOT NULL,
  `NotaDebito_FechaEmision_Comprobante` date NOT NULL,
  `NotaDebito_Consecion` varchar(500) NOT NULL,
  `NotaDebito_CodigoUnico` char(250) NOT NULL,
  `NotaDebito_ValorVenta` decimal(9,2) NOT NULL,
  `NotaDebito_IGV` decimal(9,2) NOT NULL,
  `NotaDebito_Total` decimal(9,2) NOT NULL,
  `NotaDebito_Moneda` char(5) NOT NULL,
  `NotaDebito_Lectura` varchar(500) NOT NULL,
  `NotaDebito_Fecha_Registrado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `NotaDebito_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `NotaDebito_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Empresa_Id` int(11) NOT NULL DEFAULT '1',
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `NotaDebito_DescripcionMotivo` varchar(250) NOT NULL,
  `TipoNotaDebito_Id` int(11) NOT NULL,
  PRIMARY KEY (`NotaDebito_Id`),
  UNIQUE KEY `NotaDebito_NroNotaDebito` (`NotaDebito_NumeroNotaDebito`) USING BTREE,
  KEY `fdgfd` (`Personal_Id`),
  KEY `fdgfdbb` (`Empresa_Id`),
  KEY `fdgfdbjkb` (`Estado_Id`),
  KEY `sdfsdfff` (`TipoNotaDebito_Id`),
  KEY `proverdofrfg` (`ProveedorServicio_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `nota_debito`
--
DROP TRIGGER IF EXISTS `NotaDebito_Agregar_sp`;
DELIMITER $$
CREATE TRIGGER `NotaDebito_Agregar_sp` AFTER INSERT ON `nota_debito` FOR EACH ROW INSERT INTO notadebito_electronica(NotaDebito_Id) VALUES(new.NotaDebito_Id)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pago_personal`
--

DROP TABLE IF EXISTS `pago_personal`;
CREATE TABLE IF NOT EXISTS `pago_personal` (
  `PagoPersonal_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Contrato_Id` int(11) NOT NULL,
  `PagoPersonal_Fecha` date NOT NULL,
  `PagoPersonal_Monto` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '10',
  `Empresa_Id` int(11) NOT NULL,
  PRIMARY KEY (`PagoPersonal_Id`),
  KEY `estadofkk` (`Estado_Id`),
  KEY `estadaaaofkk` (`Contrato_Id`),
  KEY `estadaaaofaskk` (`Empresa_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `pago_personal`
--
DROP TRIGGER IF EXISTS `PagoPersonal_eliminar_sp`;
DELIMITER $$
CREATE TRIGGER `PagoPersonal_eliminar_sp` BEFORE DELETE ON `pago_personal` FOR EACH ROW UPDATE adelanto_personal as ap set ap.Estado_Id=8,ap.PagoPersonal_Id=null WHERE ap.PagoPersonal_Id=old.PagoPersonal_Id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `pago_transporte`
--

DROP TABLE IF EXISTS `pago_transporte`;
CREATE TABLE IF NOT EXISTS `pago_transporte` (
  `PagoTransporte_Id` int(11) NOT NULL AUTO_INCREMENT,
  `PagoTransporte_Fecha` date NOT NULL,
  `PagoTransporte_NroFactura` char(30) NOT NULL,
  `PagoTransporte_Peso` decimal(9,2) NOT NULL,
  `PagoTransporte_Precio` decimal(9,2) NOT NULL,
  `PagoTransporte_Total` decimal(9,2) NOT NULL,
  `PagoTransporte_Detraccion` decimal(9,2) NOT NULL,
  `PagoTransporte_Descontar` varchar(20) NOT NULL,
  `PagoTransporte_Adelanto` decimal(9,2) NOT NULL,
  `PagoTransporte_Importe` decimal(9,2) NOT NULL,
  `PagoTransporte_NroOperacion` char(150) NOT NULL,
  `PagoTransporte_FechaPago` date DEFAULT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `PagoTransporte_FechaRegistro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PagoTransporte_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `PagoTransporte_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `PagoTransporte_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `ProveedorServicio_Id` int(11) NOT NULL,
  `TipoCliente_Id` int(11) NOT NULL,
  `Personal_Id` int(11) NOT NULL,
  `PagoTransporte_Estado` varchar(30) NOT NULL DEFAULT 'No Pagado',
  `PagoTransporte_RutaBaucher` text,
  `Empresa_Id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`PagoTransporte_Id`),
  KEY `asdasd` (`Estado_Id`),
  KEY `asdasassaasd` (`Personal_Id`),
  KEY `asdasassaaspppppd` (`ProveedorServicio_Id`),
  KEY `asdasassaasppppoopd` (`TipoCliente_Id`),
  KEY `asdassdsadsaddd` (`Empresa_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
CREATE TABLE IF NOT EXISTS `permisos` (
  `Permisos_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Permisos_Descripcion` varchar(100) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `Empresa_Id` int(11) NOT NULL,
  PRIMARY KEY (`Permisos_Id`),
  KEY `adasdasdas` (`Empresa_Id`),
  KEY `adasdassss` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permisos`
--

INSERT INTO `permisos` (`Permisos_Id`, `Permisos_Descripcion`, `Estado_Id`, `Empresa_Id`) VALUES
(1, 'PERSONAL', 1, 1),
(2, 'CLIENTE ENTRANTE', 1, 1),
(3, 'PROVEEDOR SERVICIO', 1, 1),
(4, 'LIQUIDACIÓN', 1, 1),
(5, 'PAGO TRANSPORTE', 1, 1),
(6, 'FACTURACION', 1, 1),
(7, 'GASTOS EXTRAS', 1, 1),
(8, 'ADELANTOS', 1, 1),
(9, 'VALORIZACION', 1, 1),
(10, 'TAREAS DIARIAS', 1, 1),
(11, 'ELECTRONICAS', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
CREATE TABLE IF NOT EXISTS `personal` (
  `Personal_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Personal_Nombres` varchar(80) NOT NULL,
  `Personal_Apellidos` varchar(80) NOT NULL,
  `Personal_DNI` char(8) NOT NULL,
  `Personal_Sexo` char(1) NOT NULL,
  `Personal_Telefono` char(15) NOT NULL,
  `Personal_Email` varchar(200) NOT NULL,
  `Personal_Direccion` varchar(300) NOT NULL,
  `TipoPersonal_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Personal_Sueldo` decimal(9,0) DEFAULT NULL,
  `Personal_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Personal_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Personal_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Fecha_Nacimiento` date DEFAULT NULL,
  `Personal_Fecha_Pago` date DEFAULT NULL,
  `Personal_Fecha_Ingreso` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Personal_TotalDiasPago` int(11) DEFAULT NULL,
  `Personal_Password` varchar(200) DEFAULT NULL,
  `Empresa_Id` int(11) NOT NULL,
  `Personalk_EstadoNotificacion` varchar(50) NOT NULL DEFAULT 'Sin Click ',
  PRIMARY KEY (`Personal_Id`),
  UNIQUE KEY `Personal_DNI` (`Personal_DNI`),
  KEY `adasd` (`Estado_Id`),
  KEY `adasdfsdsd` (`TipoPersonal_Id`),
  KEY `sadasaaaaa` (`Empresa_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal`
--

INSERT INTO `personal` (`Personal_Id`, `Personal_Nombres`, `Personal_Apellidos`, `Personal_DNI`, `Personal_Sexo`, `Personal_Telefono`, `Personal_Email`, `Personal_Direccion`, `TipoPersonal_Id`, `Estado_Id`, `Personal_Sueldo`, `Personal_Fecha_Registrado`, `Personal_Fecha_Actualizado`, `Personal_Fecha_Eliminado`, `Personal_Fecha_Nacimiento`, `Personal_Fecha_Pago`, `Personal_Fecha_Ingreso`, `Personal_TotalDiasPago`, `Personal_Password`, `Empresa_Id`, `Personalk_EstadoNotificacion`) VALUES
(31, 'LEVI', 'VELASQUEZ PAZ', '73531482', 'M', '953187894', 'l953187894@gmail.com', 'los paujiles', 30, 1, '1285', '2019-02-26 20:15:10', NULL, NULL, '1998-03-05', '2019-02-15', '2019-02-26 20:15:10', 30, 'Eduarmarina*', 1, 'Clickeado'),
(35, 'ANTONIo', 'ABURTO', '60196417', 'M', '953187894', 'asdsa', 'TRUJILLO', 30, 3, NULL, '2019-03-30 20:56:48', NULL, NULL, '2019-03-04', NULL, '2019-03-30 20:56:48', NULL, '123', 1, 'Sin Click '),
(36, 'SUMNER', 'INGA CAMPOS', '80566239', 'M', '', 'l953187894@gmail.com', 'TRUJILLO', 28, 3, NULL, '2019-05-18 19:24:36', NULL, NULL, '2019-05-07', NULL, '2019-05-18 19:24:36', NULL, 'Eduarmarina*', 1, 'Sin Click ');

--
-- Triggers `personal`
--
DROP TRIGGER IF EXISTS `Personal_insert`;
DELIMITER $$
CREATE TRIGGER `Personal_insert` AFTER INSERT ON `personal` FOR EACH ROW BEGIN
SET @contador :=1;
 while @contador <= 11 do
  INSERT INTO detalle_permiso_personal(Personal_Id,Empresa_Id,Permisos_Id) VALUES (NEW.Personal_Id,NEW.Empresa_Id,@contador);
   SET @contador := @contador + 1;
 end while;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `procedencia`
--

DROP TABLE IF EXISTS `procedencia`;
CREATE TABLE IF NOT EXISTS `procedencia` (
  `Procedencia_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Procedencia_Descripcion` varchar(80) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Procedencia_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Procedencia_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Procedencia_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`Procedencia_Id`),
  UNIQUE KEY `Procedencia_Descripcion` (`Procedencia_Descripcion`),
  KEY `asdASDASDasd` (`Personal_Id`),
  KEY `asasdasdasddas` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `procedencia`
--

INSERT INTO `procedencia` (`Procedencia_Id`, `Procedencia_Descripcion`, `Estado_Id`, `Procedencia_Fecha_Registrado`, `Procedencia_Fecha_Actualizado`, `Procedencia_Fecha_Eliminado`, `Personal_Id`) VALUES
(1, 'PATAZ', 1, '2019-04-06 15:23:50', NULL, NULL, 31),
(2, 'PARCOY\r\n', 1, '2019-04-06 15:23:50', NULL, NULL, 31),
(3, 'RETAMAS', 1, '2019-04-06 15:23:50', NULL, NULL, 31),
(4, 'LA SOLEDAD', 1, '2019-04-06 15:23:50', NULL, NULL, 31),
(5, 'LLACUABAMBA', 1, '2019-04-06 15:23:50', NULL, NULL, 31);

-- --------------------------------------------------------

--
-- Table structure for table `proveedor_servicio`
--

DROP TABLE IF EXISTS `proveedor_servicio`;
CREATE TABLE IF NOT EXISTS `proveedor_servicio` (
  `ProveedorServicio_Id` int(11) NOT NULL AUTO_INCREMENT,
  `ProveedorServicio_Razon_Social` varchar(250) DEFAULT NULL,
  `ProveedorServicio_Ruc` varchar(50) DEFAULT NULL,
  `ProveedorServicio_Entidad` char(150) DEFAULT NULL,
  `ProveedorServicio_Cuenta` char(70) DEFAULT NULL,
  `ProveedorServicio_Telefono` char(15) DEFAULT NULL,
  `ProveedorServicio_Direccion` varchar(200) DEFAULT NULL,
  `ProveedorServicio_Email` varchar(100) DEFAULT NULL,
  `TipoProveedor_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `ProveedorServicio_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ProveedorServicio_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `ProveedorServicio_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) DEFAULT NULL,
  `ProveedorServicio_TipoDocumento` varchar(20) NOT NULL DEFAULT '6',
  `Empresa_Id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ProveedorServicio_Id`),
  UNIQUE KEY `ProveedorServicio_Ruc` (`ProveedorServicio_Ruc`),
  KEY `asdasd` (`Estado_Id`),
  KEY `asdssasd` (`TipoProveedor_Id`),
  KEY `Personal_Id` (`Personal_Id`),
  KEY `asdasaassssdasd` (`Empresa_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `proveedor_servicio`
--

INSERT INTO `proveedor_servicio` (`ProveedorServicio_Id`, `ProveedorServicio_Razon_Social`, `ProveedorServicio_Ruc`, `ProveedorServicio_Entidad`, `ProveedorServicio_Cuenta`, `ProveedorServicio_Telefono`, `ProveedorServicio_Direccion`, `ProveedorServicio_Email`, `TipoProveedor_Id`, `Estado_Id`, `ProveedorServicio_Fecha_Registrado`, `ProveedorServicio_Fecha_Actualizado`, `ProveedorServicio_Fecha_Eliminado`, `Personal_Id`, `ProveedorServicio_TipoDocumento`, `Empresa_Id`) VALUES
(1, 'MINERA TITAN DEL PERU S R L', '20460352674', '', '', '983332468', 'CALLE LIBERTAD NRO 114 OFIC 3E - MIRAFLORES - LIMA - LIMA', 'l953187894@gmail.com', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(2, 'MINERA VETA DORADA S A C', '20536126440', '', '', '953187894', 'AV. LOS PAREDONES N° 569 NASCA - NASCA - ICA', 'l953187894@gmail.com;\njenss_155lara@hotmail.com', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(3, 'YPARRAGUIRRE CORREA ISAI', '10182227108', 'BCP', '10202000202', '920184612', 'CALLE: LOS PINOS MZA. 25A - LOTE 18 - URB. LA RINCONADA - LA LIBERTAD', '-', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(4, 'TRANSPORTES BERRIOS S.A.C', '20477555811', 'BCP', '5701979112004', '947420413', 'CAL 04 - MZA. F - LOTE 13 - A.H. HUERTA BELLA - TRUJILLO - TRUJILLO - LA LIBERTAD', 'transporte.berrios12@gmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(5, 'DISTRIBUCIONES GLOBAL CARGO S.A.C', '20600543041', 'BCP', '570-2268-277062', '995980797', 'AV. VILLAREAL N°  1555 URB - LA RINCONADA - LA LIBERTAD - TRUJILLO - TRUJILLO', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(6, 'TRANSPORTE TERRONES E HIJOS S.A.C', '20482821996', '', '', '997730007', 'MZA. 14 INT. 401 LOTE 10 URB. LOS JARDINES DEL GOLF LA LIBERTAD -TRUJILLO  - VICTOR LARCO HERRERA', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(7, 'TRANSPORTES BOVALCAR S.A.C', '20482613889', 'BCP', '5702053904078', '948477046', 'AV. LA PAZ.  #887 DPTO 401 - LIMA - LIMA - MIRAFLORES', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(8, 'TRANSPORTES Y MULTISERVICIOS MIKYNOR S.A.C', '20601505984', 'BANCO INTERBAN', '600-3001266982', '949840200', 'AV. LA PAZ NRO. 887 DPTO 401 - LIMA - LIMA - MIRAFLORES', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(9, 'TRANSPORTES NORIEGA', '10194187969', 'BCP - CUENTA CORRIENTE EN SOLES', '510-2296305-0-73', '', 'CALLE LAS TURQUEZAS MZA. 50 LTE. 15 - URB. LA RINCONADA - LA LIBERTAD - TRUJILLO - TRUJIILO', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(10, 'INVERSIONES GENERALES \"LUISITO\"', '20477207961', '', '', '', 'JR. CRISTOBAL LOZANO #831 - URB. EL BOSQUE - TRUJILLO - TRUJIILO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(11, 'EMPRESA DE TRANSPORTE Y SERVICIOS IPARRAGUIRRE S.A.C', '20482183698', 'SCOTIABANK PERU S.A.C', '009 - 407 - 207108060141-81', '', 'PJ LOS RUBIES MZA. 02 LOTE 17 URB LA RINCONADA - TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(12, 'GRUPO ALTOMAR E.I.R.L', '20559625885', '', '', '95897533', 'JR. MARISCAL DE ORBEGOSO  N° 210 - HUAMACHUCO - SANCHEZ CARRION - LA LIBERTAD', 'servimul.grupoaltomareirl@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(13, 'TRANSPORTES GLOBAL CARGO S.A.C', '20477223223', '', '', '997730007', 'MZA. 14 INT. 401 LOTE 10 URB. LOS JARDINES DEL GOLF LA LIBERTAD -TRUJILLO  - VICTOR LARCO HERRERA', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(14, 'VASQUEZ VALDERRAMA TITO JOSELITO', '10194195295', 'BCP', '57027558796020', '949162355', 'MZ. 29 LOTE-A URB LA RINCONADA - LA LIBERTAD - TRUJILLO - TRUJILLO', 'joselitovv@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(15, 'ETCAM', '10701871249', '', '', '979838427', 'JR. MICAELA BASTIDAS N° 1249 - A.H. CENTRAL BA. 2 - LA LIBERTAD TRUJILLO - EL PORVENIR', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(16, 'MULTISERVICIOS KAXICLAN\'S S.A.C.', '20491768992', '', '', '943777756', 'AV. LA PAZ N° 887 DPTO. 401-LIMA-MIRAFLORES', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(17, 'RODRIGUEZ ACOSTA JUAN', '10103829807', 'BCP - JUAN RODRIGUEZ A CTA AHORROS', '57033282342081', '994915356', 'AV. SANCHEZ CARRION N° 1513 P.J. EL PORVENIR - LA LIBERTAD - TRUJILLO - EL PORVENIR', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(18, 'DEOCHRYSO SAC', '20552822006', '', '', '', 'AV. JOSE DE LA RIVA AGUERO N° 408 OFIC. 3ER PISO EL AGUSTINO - LIMA', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(19, 'MATOS DELGADO MERCEDES', '10194071391', '', '', '975457181', 'CALLE RAFAEL SANZIO N° 1001 - SANTA ROSA - TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(20, 'EXPLORACIONES YONAR E.I.R.L', '20540020338', '', '', '949059741', 'CAL. BARRIO BAJO S/N - LA LIBERTAD - PATAZ - PATAZ', 'exploraciones.yonar@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(21, 'GARCIA CAVERO MAURA', '10190310987', 'BCP', '57021873982084', '947846466', 'CALLE RODONITAZ MZ. 54 - LT 20 - UBR. LA RINCONADA - TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(22, 'SILVER CASCAS SAC', '20549929631', '', '', '', 'JR SOLEDAD NRO 325 INT 301 LINCE - LIMA', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(23, 'TRANSPORTES Y MULTISERVICIOS A & G S.A.C', '20477340378', 'BCP', '5702180929058', '954161363', 'URB. SANTO DOMINGUITO - TRUJILLO - TRUJILLO - LA LIBERTAD', 'transportes.multiservicios.ayg@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(24, 'TRANSPORTES & COMERCIAL LUCHIN E.I.R.L.', '20481591920', 'BCP', '5702021162052', '949467426', 'LOS GERANIOS MZ. B LOTE 2 URB. MANPUESTO - TRUJILLO - TRUJILLO - LA LIBERTAD', 'transportesluchin@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(25, 'CONSUR TRADING S.A.C', '20535120464', '', '', '', 'JR. ARICA N° 601 NASCA - NASCA - ICA', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(26, 'MINERA LAYTARUMA S.A.', '20125959483', '-', '-', '', 'JR.TIZIANO NRO.301URB.SAN BORJA(A3 CDRAS DEL CINE AVIACION)LIMA-LIMA-SAN BORJA', '-', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(27, 'BAILON MALQUI CESAR AUGUSTO', '10440768224', 'BCP', '570-2449641-0-22', '', 'AV. LOS JASMINES N° S/N ANX. LLACUABAMBA - PARCOY - PATAZ - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(28, 'INVERSIONES GENERALES JVR S.A.C.', '20481310181', 'SCOTIABANK', '4031300', '', 'AV. SANCHEZ CARRION N\" 1547 P,J, EL PORVENIR - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(29, 'GUZMAN HUILLA JHON PHOOL', '10724418355', '', '', '', 'MZ.O LT. 02 - URB. LIBERTAD - TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(30, 'MARIÑOS JULIAN MARIO NELVER ', '10191026140', 'BANCO DE LA NACIÓN', '04807138931', '949570408', 'CAL. PIURA N° 444 BARRIO ARANJUEZ - TRUJILLO - TRUJILLO -  LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(31, 'MINERA LAS LOMAS DORADAS S.A.C', '20543412709', '', '', '', 'JR. LOS LAURELES NRO. 126 INT. 205B URB. VALLE HERMOSO SURCO - LIMA', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(32, 'FENIX DE ACERO S.A.C', '20560101148', 'BCP', '570-2265140-0-76', '966839604', 'AV, TAHUANTISUYO N° 905 CENTRAL BA, 3,', 'fenixdeacerosac@hotmail,com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(33, 'POR ASIGNAR', '2020202020', '85', '', '01', '-', '-', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(34, 'LA JOYA MINING S.A.C.', '20539627938', '', '', '', 'PJ. BERNARDO ÁLCIDO, SUBLOTE A-1 NRO. 101 AREQUIPA - AREQUIPA - AREQUIPA', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(35, 'CAMPOS DOMINGUEZ WILFORD EDUARDO', '10195367812', 'BCP', '57038056785050', '999696665', 'AV. PUMACAHUA N° 2306 INT. B.P.J. PORVENIR - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(36, 'CONSTRUCTORA GUZTRU S.A.C', '20540073296', '', '', '982732699', 'MZA. G INT. 2°PI LOTE. 33 URB LA RINCONADA III ETAPA - LA LIBERTAD - TRUJILLO - TRUJILLO', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(37, 'EMPRESA DE TRANSPORTES Y SERVICIOS GENERALES CHACON', '20559867897', 'BCP', '5702151291016', '948165799', '', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(38, 'CONSTRUCTURA Y SERVICIOS GENERALES JENSOL S.A.C', '20482512268', 'CONTINENTAL', '001102490100160786', '980962497', 'CHILIA - PATAZ - LA LIBERTAD', 'joel140380@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(39, 'ROJAS RODRIGUEZ ROBIN JIMY', '10408520270', 'BCP', '57018793951042', '948360061', 'MZ. B - LT2 - SEMIRUSTICA MANUPUESTO - TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(40, 'MENDOZA ALVARADO CARMILIO', '10230823567', 'BCP - MENDOZA VIERA', '57035617190012', '990711167', 'CALLE CIRO ALEGRIA N° 322 DPTO. 202- URB LAS QUINTANAS - TRUJILLO - LA LIBERTAD', 'JOZEPH1117@GMAIL.COM', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(41, 'PERLAMAYO S.R.L', '20529433451', 'BANCO CONTINENTAL', '0011-0250-0100050552-87', '', '', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(42, 'TRANSPORTES Y MULTISERVICIOS TITO SAC', '20601544653', 'INTERBANK', '003-616-3001298531', '948310396', 'MZ. 29 LOTE 15-A LA RINCONADA ', 'joselitovv@hotmail.com', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(43, 'FERNANDEZ RUIZ NOE CLEIDER', '10449008168', 'BCP', '570-038210807-0-29', '978704321', 'MZ. G LOTE. 30 LOS LAURELES - FLORENCIA DE MORA', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(44, 'VIERA CAMPOS JULIO', '10230822285', 'BCP', '570-35617190-0-12', '910511882', 'HUACACHUCRO', 'JOZEPH1117@GMAIL.COM', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(45, 'TRANSPORTES E INVERSIONES DUEÑES S.A.C', '20539833759', 'CONTINENTAL', '0011-0249-03-0201514694', '', '', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(46, 'POR CAMBIAR', '11111111111', '', '', '', '', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(47, 'MALDONADO HONORIO CRESENCIO', '10194181553', 'BCP', '57022889165021', '948481222', 'MZA. F LOTE 36 A.H. NUEVA FLORENCIA I - FLORENCIA DE MORA - TRUJILLO', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(48, 'SERVICIOS GENERALES MAVISEL S.A.C', '20477331115', 'CONTINENTAL', '001102490100152201', '', 'AV, MIRAFLORES N° 731 URB. MIRAFLORES -TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(49, 'CAMPOS DOMINGUEZ WILLAR HUMBERTO', '10442014952', 'MI BANCO', '600-5320281', '', 'CALLE PUMACAHUA N° 2306 - B - LA LIBERTAD - TRUJILLO - EL PORVENIR', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(50, 'MILLENIUM J & J S.A.C', '20602808972', 'CONTINENTAL', '0011-0249-0100167179', '', 'JR. CRISTOBAL LOZANO #831 URB EL BOSQUE - TRUJILLO - TRUJILLO - LA LIBERTAD', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(51, 'GAMACMIN S.A.C.', '20451534107', '', '', '', 'CAL.LEONIDAS YEROVI NRO. 106 DPTO. 93 URB. SANTA ISABEL LIMA - LIMA - SAN ISIDRO', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(52, 'MINERA META SEGURA S.A.C.', '20491877180', '', '', '', 'CAL.MARCHAND NRO. 453 URB. SAN BORJA  LIMA - LIMA - SAN BORJA', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(53, 'WILBER ANDRES TALAVERA TALAVERA ', '10293485319', 'BCP', '191-16071008041', '5266087', 'CALLE MARIANO MELGAR N°521 INT. C URB LA LIBERTAD- CERRO COLORADO AREQUIPA', '', 1, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1),
(54, 'JINGZHONG GLOBLE MINING COMPANY S.A.C.', '20603172656', '', '', '', 'PRO.HUANUCO NRO. 2010 INT. 802 URB. SAN PABLO - LIMA - LIMA - LA VICTORIA ', '', 2, 1, '2019-04-06 15:14:39', NULL, NULL, 31, '6', 1);

-- --------------------------------------------------------

--
-- Table structure for table `reintegro`
--

DROP TABLE IF EXISTS `reintegro`;
CREATE TABLE IF NOT EXISTS `reintegro` (
  `Reintegro_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Reintegro_Fecha` date NOT NULL,
  `ClienteEntrante_Id` int(11) NOT NULL,
  `Procedencia_Id` int(11) NOT NULL,
  `Reintegro_Lote` char(50) NOT NULL,
  `Reintegro_Tmh` decimal(9,3) NOT NULL,
  `Reintegro_H2o` decimal(9,2) NOT NULL,
  `Reintegro_LeyAu` decimal(9,3) NOT NULL,
  `Reintegro_LeyAg` decimal(9,3) NOT NULL,
  `Reintegro_Inter` decimal(9,2) NOT NULL,
  `Reintegro_Rec` decimal(9,2) NOT NULL,
  `Reintegro_Maquilla` decimal(9,2) NOT NULL,
  `Reintegro_Factor` decimal(9,4) NOT NULL,
  `Reintegro_Conscon` decimal(9,2) NOT NULL,
  `Reintegro_Escalador` decimal(9,2) NOT NULL,
  `Reintegro_Stms` decimal(9,2) NOT NULL,
  `Reintegro_TotalUs` decimal(9,2) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `Liquidacion_Id` int(11) NOT NULL,
  `Reintegro_Reintegro` decimal(9,3) NOT NULL,
  `Reintegro_Pago` char(50) NOT NULL,
  `Reintegro_Tms` decimal(9,3) NOT NULL,
  PRIMARY KEY (`Reintegro_Id`),
  KEY `asdaaasdas` (`Estado_Id`),
  KEY `asdaaasdassss` (`ClienteEntrante_Id`),
  KEY `asdaaasdasssas` (`Procedencia_Id`),
  KEY `asdaaasdasssaaas` (`Liquidacion_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Triggers `reintegro`
--
DROP TRIGGER IF EXISTS `Reintegro_agregar_dispa`;
DELIMITER $$
CREATE TRIGGER `Reintegro_agregar_dispa` BEFORE INSERT ON `reintegro` FOR EACH ROW UPDATE liquidacion as l set l.Liquidacion_Reintegro="SI" WHERE l.Liquidacion_Id=new.Liquidacion_Id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `rendicion`
--

DROP TABLE IF EXISTS `rendicion`;
CREATE TABLE IF NOT EXISTS `rendicion` (
  `Rendicion_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Rendicion_Descripcion` varchar(200) NOT NULL,
  `Rendicion_Fecha` date NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `Rendicion_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Rendicion_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `Rendicion_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`Rendicion_Id`),
  KEY `asdasd` (`Estado_Id`),
  KEY `Personal_Id` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reporte_ruta`
--

DROP TABLE IF EXISTS `reporte_ruta`;
CREATE TABLE IF NOT EXISTS `reporte_ruta` (
  `ReporteRuta_Id` int(11) NOT NULL AUTO_INCREMENT,
  `RutaReporte_AdelantoCliente` varchar(1024) NOT NULL,
  `RutaReporte_AdelantoProveedor` varchar(1024) NOT NULL,
  `RutaReporte_Cheque` varchar(1024) NOT NULL,
  `RutaReporte_Factura` varchar(1024) NOT NULL,
  `RutaReporte_Liquidacion` varchar(1024) NOT NULL,
  `RutaReporte_PagoTransporte` varchar(1024) NOT NULL,
  `RutaReporte_Valorizacion` varchar(1024) NOT NULL,
  `RutaReporte_NotaDebito` varchar(1024) NOT NULL,
  `RutaReporte_NotaCredito` varchar(1024) NOT NULL,
  `Empresa_Id` int(11) NOT NULL,
  PRIMARY KEY (`ReporteRuta_Id`),
  KEY `ssssss` (`Empresa_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reporte_ruta`
--

INSERT INTO `reporte_ruta` (`ReporteRuta_Id`, `RutaReporte_AdelantoCliente`, `RutaReporte_AdelantoProveedor`, `RutaReporte_Cheque`, `RutaReporte_Factura`, `RutaReporte_Liquidacion`, `RutaReporte_PagoTransporte`, `RutaReporte_Valorizacion`, `RutaReporte_NotaDebito`, `RutaReporte_NotaCredito`, `Empresa_Id`) VALUES
(1, 'C:\\SistemaYersica\\AdelantoReporte\\Cliente\\', 'C:\\SistemaYersica\\AdelantoReporte\\ProveedorServicios\\', 'C:\\SistemaYersica\\ChequeReporte\\', 'C:\\SistemaYersica\\FacturaReporte\\', 'C:\\SistemaYersica\\LiquidacionReporte\\', 'C:\\SistemaYersica\\PagoTransporteReporte\\', 'C:\\SistemaYersica\\ValorizacionReporte\\', 'C:\\SistemaYersica\\NotaDebitoReporte\\', 'C:\\SistemaYersica\\NotaCreditoReporte\\', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tarea_diaria`
--

DROP TABLE IF EXISTS `tarea_diaria`;
CREATE TABLE IF NOT EXISTS `tarea_diaria` (
  `TareaDiaria_Id` int(11) NOT NULL AUTO_INCREMENT,
  `TareaDiaria_Descripcion` varchar(450) NOT NULL,
  `Empresa_Id` int(11) NOT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '12',
  `TareaDiaria_Fecha` date NOT NULL,
  `TareaDiaria_EstadoNotificacion` varchar(50) NOT NULL DEFAULT 'Sin Click',
  PRIMARY KEY (`TareaDiaria_Id`),
  KEY `asdsasssssdas` (`Empresa_Id`),
  KEY `asdsasssssdaaaas` (`Estado_Id`),
  KEY `asdsasssssdaaaasaas` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tipo_cliente`
--

DROP TABLE IF EXISTS `tipo_cliente`;
CREATE TABLE IF NOT EXISTS `tipo_cliente` (
  `TipoCliente_Id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoCliente_Descripcion` varchar(50) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `TipoCliente_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TipoCliente_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `TipoCliente_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`TipoCliente_Id`),
  UNIQUE KEY `TipoCliente_Descripcion` (`TipoCliente_Descripcion`),
  KEY `hola` (`Personal_Id`),
  KEY `estadofk` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_cliente`
--

INSERT INTO `tipo_cliente` (`TipoCliente_Id`, `TipoCliente_Descripcion`, `Estado_Id`, `TipoCliente_Fecha_Registrado`, `TipoCliente_Fecha_Actualizado`, `TipoCliente_Fecha_Eliminado`, `Personal_Id`) VALUES
(1, 'POR FACTURACION', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(2, 'COMPRA', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(3, 'PRODUCCION', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(4, 'COBRE', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(5, 'TRABAJADOR', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(6, 'CONSTRUCCION', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(7, 'INVERSION', 1, '2019-04-01 22:28:27', NULL, NULL, 31),
(8, 'PRESTAMOS', 1, '2019-04-01 22:28:27', NULL, NULL, 31);

-- --------------------------------------------------------

--
-- Table structure for table `tipo_factura_electronica`
--

DROP TABLE IF EXISTS `tipo_factura_electronica`;
CREATE TABLE IF NOT EXISTS `tipo_factura_electronica` (
  `TipoFacturaElectronica_Id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoFacturaElectronica_Descripcion` varchar(50) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  PRIMARY KEY (`TipoFacturaElectronica_Id`),
  KEY `asdasdfffff` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_factura_electronica`
--

INSERT INTO `tipo_factura_electronica` (`TipoFacturaElectronica_Id`, `TipoFacturaElectronica_Descripcion`, `Estado_Id`) VALUES
(3, 'Factura', 1),
(4, 'Boleta', 2),
(5, 'NC', 2),
(6, 'ND', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tipo_nota_credito`
--

DROP TABLE IF EXISTS `tipo_nota_credito`;
CREATE TABLE IF NOT EXISTS `tipo_nota_credito` (
  `TipoNotaCredito_id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoNotaCredito_descripcion` varchar(100) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  PRIMARY KEY (`TipoNotaCredito_id`),
  KEY `asdasdassssdas` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_nota_credito`
--

INSERT INTO `tipo_nota_credito` (`TipoNotaCredito_id`, `TipoNotaCredito_descripcion`, `Estado_Id`) VALUES
(1, 'ANULACIÓN DE LA OPERACION', 1),
(2, 'ANULACIÓN POR ERROR EN EL RUC', 1),
(3, 'CORRECCIÓN POR ERROR EN LA DESCRIPCIÓN', 1),
(4, 'DESCUENTO GLOBAL', 1),
(5, 'DESCUENTO ITEM', 1),
(6, 'DEVOLUCIÓN TOTAL', 1),
(7, 'DEVOLUCIÓN PARCIAL', 1),
(8, 'BENIFICACIÓN', 1),
(9, 'DISMINUCIÓN EN EL VALOR', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tipo_nota_debito`
--

DROP TABLE IF EXISTS `tipo_nota_debito`;
CREATE TABLE IF NOT EXISTS `tipo_nota_debito` (
  `TipoNotaDebito_Id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoNotaDebito_Descripcion` varchar(100) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  PRIMARY KEY (`TipoNotaDebito_Id`),
  KEY `asdqqqqqqasdas` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_nota_debito`
--

INSERT INTO `tipo_nota_debito` (`TipoNotaDebito_Id`, `TipoNotaDebito_Descripcion`, `Estado_Id`) VALUES
(1, 'INTERES POR MORA', 1),
(2, 'AUMENTO EN EL VALOR', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tipo_personal`
--

DROP TABLE IF EXISTS `tipo_personal`;
CREATE TABLE IF NOT EXISTS `tipo_personal` (
  `TipoPersonal_Id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoPersonal_Descripcion` varchar(50) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `TipoPersonal_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TipoPersonal_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `TipoPersonal_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TipoPersonal_Id`),
  UNIQUE KEY `uniquesss` (`TipoPersonal_Descripcion`),
  UNIQUE KEY `TipoPersonal_Descripcion` (`TipoPersonal_Descripcion`),
  KEY `asdas` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_personal`
--

INSERT INTO `tipo_personal` (`TipoPersonal_Id`, `TipoPersonal_Descripcion`, `Estado_Id`, `TipoPersonal_Fecha_Registrado`, `TipoPersonal_Fecha_Actualizado`, `TipoPersonal_Fecha_Eliminado`) VALUES
(28, 'ADMINISTRADOR', 1, '2019-02-06 16:49:48', NULL, NULL),
(29, 'CONTADBILIDAD', 1, '2019-03-30 19:50:12', NULL, NULL),
(30, 'ACOPIADOR', 1, '2019-03-30 19:50:22', NULL, NULL),
(31, 'CONDUCTOR', 1, '2019-03-30 19:50:31', NULL, NULL),
(32, 'ENCARGADO DE COMERCIALIZACION', 1, '2019-03-30 19:51:35', NULL, NULL),
(33, 'GERENTE GENERAL', 1, '2019-04-01 22:11:05', NULL, NULL),
(34, 'JEFE DE AREA', 1, '2019-04-01 22:11:05', NULL, NULL),
(36, 'SUPERVISOR DE PROCESO', 1, '2019-04-01 22:11:05', NULL, NULL),
(37, 'OFICINA', 1, '2019-05-18 19:25:28', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tipo_proveedor`
--

DROP TABLE IF EXISTS `tipo_proveedor`;
CREATE TABLE IF NOT EXISTS `tipo_proveedor` (
  `TipoProveedor_Id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoProveedor_Descripcion` varchar(80) NOT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `TipoProveedor_Fecha_Registrado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `TipoProveedor_Fecha_Actualizado` timestamp NULL DEFAULT NULL,
  `TipoProveedor_Fecha_Eliminado` timestamp NULL DEFAULT NULL,
  `Personal_Id` int(11) NOT NULL,
  PRIMARY KEY (`TipoProveedor_Id`),
  UNIQUE KEY `TipoProveedor_Descripcion` (`TipoProveedor_Descripcion`),
  KEY `tipovo` (`Personal_Id`),
  KEY `asdasd` (`Estado_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipo_proveedor`
--

INSERT INTO `tipo_proveedor` (`TipoProveedor_Id`, `TipoProveedor_Descripcion`, `Estado_Id`, `TipoProveedor_Fecha_Registrado`, `TipoProveedor_Fecha_Actualizado`, `TipoProveedor_Fecha_Eliminado`, `Personal_Id`) VALUES
(1, 'TRANSPORTE', 1, '2019-04-06 14:59:44', NULL, NULL, 31),
(2, 'POR FACTURACION', 1, '2019-04-06 14:59:44', NULL, NULL, 31),
(3, 'TRABAJADOR', 1, '2019-04-06 14:59:44', NULL, NULL, 31);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `Usuario_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario_Usuario` varchar(50) NOT NULL,
  `Usuario_Password` varchar(80) NOT NULL,
  `Personal_Id` int(11) NOT NULL,
  `Estado_Id` int(11) NOT NULL,
  `Usuario_Fecha_Registrado` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Usuario_Fecha_Actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Usuario_Fecha_Eliminado` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Usuario_Id`),
  KEY `asdasd` (`Estado_Id`),
  KEY `asdasds` (`Personal_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `valorizacion`
--

DROP TABLE IF EXISTS `valorizacion`;
CREATE TABLE IF NOT EXISTS `valorizacion` (
  `Valorizacion_codigo` int(11) NOT NULL AUTO_INCREMENT,
  `ClienteEntrante_Id` int(11) NOT NULL,
  `Valorizacion_Tmh` decimal(9,2) DEFAULT NULL,
  `Valorizacion_CostoTraTrujiSoles` decimal(9,2) DEFAULT NULL,
  `Valorizacion_CostTraNasSoles` decimal(9,2) DEFAULT NULL,
  `Valorizacion_TotalAnalisis` decimal(9,0) DEFAULT NULL,
  `Valorizacion_TotalUS` decimal(9,2) DEFAULT NULL,
  `Valorizacion_TarifaPorcentaje` decimal(9,2) DEFAULT NULL,
  `Valorizacion_Adelanto` decimal(9,2) DEFAULT NULL,
  `Valorizacion_OtrosGastos` decimal(9,2) DEFAULT NULL,
  `Valorizacion_TotalGastos` decimal(9,2) DEFAULT NULL,
  `Valorizacion_TotalPagar` decimal(9,2) DEFAULT NULL,
  `Valorizacion_Estado` varchar(20) NOT NULL,
  `Valorizacion_Fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Valorizacion_Cambio` decimal(9,2) DEFAULT NULL,
  `Valorizacion_CostoTraTruDolar` decimal(9,2) DEFAULT NULL,
  `Valorizacion_CostTraNascDolar` decimal(9,2) DEFAULT NULL,
  `Valorizacion_CLotes` decimal(9,2) DEFAULT NULL,
  `Valorizacion_TotalPorcentaje` decimal(9,2) DEFAULT NULL,
  `Valorizacion_FechaId` date NOT NULL,
  `Valorizacion_DescuentoSoles` decimal(9,2) DEFAULT NULL,
  `Estado_Id` int(11) NOT NULL DEFAULT '1',
  `Valoracion_Policia` decimal(9,2) DEFAULT NULL,
  `Valoracion_TarifaAnalisis` decimal(9,2) DEFAULT NULL,
  `Valorizacion_Toneladas` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`Valorizacion_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `adelanto`
--
ALTER TABLE `adelanto`
  ADD CONSTRAINT `as1ds11adas` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`),
  ADD CONSTRAINT `as1ds11adas22` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `as1dsadas` FOREIGN KEY (`ClienteEntrante_Id`) REFERENCES `cliente_entrante` (`ClienteEntrante_Id`),
  ADD CONSTRAINT `asdasdasdasdaaa` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `asdsadas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `adelanto_personal`
--
ALTER TABLE `adelanto_personal`
  ADD CONSTRAINT `aaaaaa` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdadasss` FOREIGN KEY (`PagoPersonal_Id`) REFERENCES `pago_personal` (`PagoPersonal_Id`),
  ADD CONSTRAINT `holaaaa` FOREIGN KEY (`Contrato_Id`) REFERENCES `contrato` (`Contrato_Id`),
  ADD CONSTRAINT `holaaaaaas` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `agrupadorfe`
--
ALTER TABLE `agrupadorfe`
  ADD CONSTRAINT `sadasdasd` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `cheque`
--
ALTER TABLE `cheque`
  ADD CONSTRAINT `aaaaaas` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `asdasdasdas` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `asdasdasdssaas` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`),
  ADD CONSTRAINT `asdasdasdssas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `cliente_entrante`
--
ALTER TABLE `cliente_entrante`
  ADD CONSTRAINT `aasdassadsasddas` FOREIGN KEY (`TipoCliente_Id`) REFERENCES `tipo_cliente` (`TipoCliente_Id`),
  ADD CONSTRAINT `aasdassddas` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `asdassssdasd` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `asddas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `sadasdaaaa` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `sadasdaaaaaa` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `sadasdaaaaggaa` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);

--
-- Constraints for table `correo_factura`
--
ALTER TABLE `correo_factura`
  ADD CONSTRAINT `asdadasdasd` FOREIGN KEY (`FacturaElectronica_Id`) REFERENCES `factura_electronica` (`FacturaElectronica_Id`),
  ADD CONSTRAINT `asdasdaasdasdsdf` FOREIGN KEY (`Estado_Id`) REFERENCES `proveedor_servicio` (`Estado_Id`),
  ADD CONSTRAINT `asdasdasdf` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`);

--
-- Constraints for table `detalle_permiso_personal`
--
ALTER TABLE `detalle_permiso_personal`
  ADD CONSTRAINT `fdddddaaad` FOREIGN KEY (`Permisos_Id`) REFERENCES `permisos` (`Permisos_Id`),
  ADD CONSTRAINT `fdddddd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `sssaass` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `sssss` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `estado_factura_electronica`
--
ALTER TABLE `estado_factura_electronica`
  ADD CONSTRAINT `fjgjgjgjgjgjjg` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `asdasasdadasd` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`),
  ADD CONSTRAINT `asdasasdasddasd` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `asdasdasd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);

--
-- Constraints for table `factura_detalle`
--
ALTER TABLE `factura_detalle`
  ADD CONSTRAINT `asdallsd` FOREIGN KEY (`Liquidacion_Id`) REFERENCES `liquidacion` (`Liquidacion_Id`),
  ADD CONSTRAINT `asdasdaaaasd` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdasdasgfgaaasd` FOREIGN KEY (`Factura_Id`) REFERENCES `factura` (`Factura_Id`);

--
-- Constraints for table `factura_electronica`
--
ALTER TABLE `factura_electronica`
  ADD CONSTRAINT `asdasdasdasda` FOREIGN KEY (`Factura_Id`) REFERENCES `factura` (`Factura_Id`),
  ADD CONSTRAINT `asdasdasdasssda` FOREIGN KEY (`EstadoFacturaElectronica_Id`) REFERENCES `estado_factura_electronica` (`EstadoFacturaElectronica_Id`);

--
-- Constraints for table `factura_numero`
--
ALTER TABLE `factura_numero`
  ADD CONSTRAINT `asda112sd` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `gastos_extras`
--
ALTER TABLE `gastos_extras`
  ADD CONSTRAINT `asdaAAsd11asff` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `asdasd11asff` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdasdasff` FOREIGN KEY (`Concepto_Id`) REFERENCES `concepto` (`Concepto_Id`);

--
-- Constraints for table `liquidacion`
--
ALTER TABLE `liquidacion`
  ADD CONSTRAINT `ASDASDAS` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `asasdasddas` FOREIGN KEY (`Procedencia_Id`) REFERENCES `procedencia` (`Procedencia_Id`),
  ADD CONSTRAINT `asdaasdaasdsdsd` FOREIGN KEY (`ClienteEntrante_Id`) REFERENCES `cliente_entrante` (`ClienteEntrante_Id`),
  ADD CONSTRAINT `asdaasdasdsd` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asooodaasdsdsd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);

--
-- Constraints for table `notacredito`
--
ALTER TABLE `notacredito`
  ADD CONSTRAINT `asdasdasdsaasdss` FOREIGN KEY (`TipoNotaCredito_Id`) REFERENCES `tipo_nota_credito` (`TipoNotaCredito_id`),
  ADD CONSTRAINT `proverdorfg` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`);

--
-- Constraints for table `notacredito_detalle`
--
ALTER TABLE `notacredito_detalle`
  ADD CONSTRAINT `asdasdaaaaa` FOREIGN KEY (`NotaCredito_Id`) REFERENCES `notacredito` (`NotaCredito_id`),
  ADD CONSTRAINT `asdasdaffaaaa` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdasdaffdddaaaa` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);

--
-- Constraints for table `notacredito_electronica`
--
ALTER TABLE `notacredito_electronica`
  ADD CONSTRAINT `sadasdasdasdasdasd` FOREIGN KEY (`EstadoNotaCredito_Id`) REFERENCES `estado_factura_electronica` (`EstadoFacturaElectronica_Id`),
  ADD CONSTRAINT `sadsadasdssssas` FOREIGN KEY (`EstadoNotaCredito_Ids`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `notadebitodetalle`
--
ALTER TABLE `notadebitodetalle`
  ADD CONSTRAINT `asdassssdas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `notadebidovfkdfdfsdfs` FOREIGN KEY (`NotaDebito_Id`) REFERENCES `nota_debito` (`NotaDebito_Id`),
  ADD CONSTRAINT `reintegrofk` FOREIGN KEY (`Reintegro_Id`) REFERENCES `reintegro` (`Reintegro_Id`);

--
-- Constraints for table `notadebito_electronica`
--
ALTER TABLE `notadebito_electronica`
  ADD CONSTRAINT `sadasdasdasd` FOREIGN KEY (`NotaDebito_Id`) REFERENCES `nota_debito` (`NotaDebito_Id`),
  ADD CONSTRAINT `sadasdasddsdasd` FOREIGN KEY (`EstadoNotaDebito_Id`) REFERENCES `estado_factura_electronica` (`EstadoFacturaElectronica_Id`);

--
-- Constraints for table `notadebito_numero`
--
ALTER TABLE `notadebito_numero`
  ADD CONSTRAINT `asdasdasddasd` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `nota_debito`
--
ALTER TABLE `nota_debito`
  ADD CONSTRAINT `fdgfd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `fdgfdbb` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `fdgfdbjkb` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `proverdofrfg` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`),
  ADD CONSTRAINT `sdfsdfff` FOREIGN KEY (`TipoNotaDebito_Id`) REFERENCES `tipo_nota_debito` (`TipoNotaDebito_Id`);

--
-- Constraints for table `pago_personal`
--
ALTER TABLE `pago_personal`
  ADD CONSTRAINT `estadaaaofaskk` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `estadaaaofkk` FOREIGN KEY (`Contrato_Id`) REFERENCES `contrato` (`Contrato_Id`),
  ADD CONSTRAINT `estadofkk` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `pago_transporte`
--
ALTER TABLE `pago_transporte`
  ADD CONSTRAINT `asdasassaasd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `asdasassaasppppoopd` FOREIGN KEY (`TipoCliente_Id`) REFERENCES `tipo_cliente` (`TipoCliente_Id`),
  ADD CONSTRAINT `asdasassaaspppppd` FOREIGN KEY (`ProveedorServicio_Id`) REFERENCES `proveedor_servicio` (`ProveedorServicio_Id`),
  ADD CONSTRAINT `asdassdsadsaddd` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `adasdasdas` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `adasdassss` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `personal`
--
ALTER TABLE `personal`
  ADD CONSTRAINT `adasd` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `adasdfsdsd` FOREIGN KEY (`TipoPersonal_Id`) REFERENCES `tipo_personal` (`TipoPersonal_Id`),
  ADD CONSTRAINT `sadasaaaaa` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `procedencia`
--
ALTER TABLE `procedencia`
  ADD CONSTRAINT `asasdasdasddas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdASDASDasd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);

--
-- Constraints for table `proveedor_servicio`
--
ALTER TABLE `proveedor_servicio`
  ADD CONSTRAINT `asdasaassssdasd` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`),
  ADD CONSTRAINT `asssssdasdasd` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `dsad` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `sadsadassssdasdsd` FOREIGN KEY (`TipoProveedor_Id`) REFERENCES `tipo_proveedor` (`TipoProveedor_Id`);

--
-- Constraints for table `reintegro`
--
ALTER TABLE `reintegro`
  ADD CONSTRAINT `asdaaasdas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdaaasdasssaaas` FOREIGN KEY (`Liquidacion_Id`) REFERENCES `liquidacion` (`Liquidacion_Id`),
  ADD CONSTRAINT `asdaaasdasssas` FOREIGN KEY (`Procedencia_Id`) REFERENCES `procedencia` (`Procedencia_Id`),
  ADD CONSTRAINT `asdaaasdassss` FOREIGN KEY (`ClienteEntrante_Id`) REFERENCES `cliente_entrante` (`ClienteEntrante_Id`);

--
-- Constraints for table `reporte_ruta`
--
ALTER TABLE `reporte_ruta`
  ADD CONSTRAINT `ssssss` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `tarea_diaria`
--
ALTER TABLE `tarea_diaria`
  ADD CONSTRAINT `asdsasssssdaaaas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `asdsasssssdaaaasaas` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`),
  ADD CONSTRAINT `asdsasssssdas` FOREIGN KEY (`Empresa_Id`) REFERENCES `empresa` (`Empresa_id`);

--
-- Constraints for table `tipo_cliente`
--
ALTER TABLE `tipo_cliente`
  ADD CONSTRAINT `estadofk` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `hola` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);

--
-- Constraints for table `tipo_nota_credito`
--
ALTER TABLE `tipo_nota_credito`
  ADD CONSTRAINT `asdasdassssdas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `tipo_nota_debito`
--
ALTER TABLE `tipo_nota_debito`
  ADD CONSTRAINT `asdqqqqqqasdas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `tipo_personal`
--
ALTER TABLE `tipo_personal`
  ADD CONSTRAINT `asdas` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`);

--
-- Constraints for table `tipo_proveedor`
--
ALTER TABLE `tipo_proveedor`
  ADD CONSTRAINT `asdasd` FOREIGN KEY (`Estado_Id`) REFERENCES `estado` (`Estado_Id`),
  ADD CONSTRAINT `tipovo` FOREIGN KEY (`Personal_Id`) REFERENCES `personal` (`Personal_Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
