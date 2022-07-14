<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="es_AR"/>
<section id="vinilos">
    <div class="container py-5 d-flex align-items-center">
        <div class="row">
            <div class="col-md-8">
                <div class="card bg-primary">
                    <div class="card-header">
                        <h4>Listado de Vinilos</h4>
                    </div>
                    <table class="table table-striped table-dark">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Autor</th>
                                <th>Cantidad de Canciones</th>
                                <th>Precio</th>
                                <th>Copias</th>
                                <th>Accion</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="vinilo" items="${vinilos}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${vinilo.nombre}</td>
                                    <td>${vinilo.autor}</td>
                                    <th>${vinilo.cantCanciones}</th>
                                    <td><fmt:formatNumber value="${vinilo.precio}" type="currency"/></td>
                                    <td>${vinilo.copias}</td>
                                    <td>
                                        <!-- NO OLVIDAR COMPLETAR HREF -->
                                        <a href="${pageContext.request.contextPath}/servletControlador?accion=editar&idvinilo=${vinilo.idvinilo}" class="btn btn-secondary">
                                            <i class="fas fa-angle-double-right"></i>
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Cantidad de vinilos</h3>
                        <h4 class="display-4">${cantidadVinilos}</h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Precio Total de Vinilos</h3>
                        <h4 class="display-4"><fmt:formatNumber value="${precioTotal}" type="currency"/></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="/WEB-INF/paginas/operaciones/agregarVinilo.jsp"/>