//package jsges.nails.controller.articulos;
//
//import jsges.nails.DTO.articulos.ArticuloVentaDTO;
//import jsges.nails.domain.articulos.ArticuloVenta;
//import jsges.nails.domain.articulos.Linea;
//import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
//import jsges.nails.service.articulos.IArticuloVentaService;
//import jsges.nails.service.articulos.ILineaService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ArticuloVentaControllerTest {
//
//    @Mock
//    private IArticuloVentaService modelService;
//
//    @Mock
//    private ILineaService lineaService;
//
//    @InjectMocks
//    private ArticuloVentaController controller;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAll_returnsListOfArticuloVentaDTO() {
//        Linea linea = new Linea();
//        linea.setId(1);
//
//        ArticuloVenta articulo1 = new ArticuloVenta();
//        articulo1.setLinea(linea);
//
//        ArticuloVenta articulo2 = new ArticuloVenta();
//        articulo2.setLinea(linea);
//
//        List<ArticuloVenta> articulos = Arrays.asList(articulo1, articulo2);
//        when(modelService.listar()).thenReturn(articulos);
//
//        List<ArticuloVentaDTO> result = controller.getAll();
//
//        assertEquals(2, result.size());
//        verify(modelService, times(1)).listar();
//    }
//
//    @Test
//    void getItems_returnsPaginatedArticuloVentaDTO() {
//        Linea linea = new Linea();
//        linea.setId(1);
//
//        ArticuloVenta articulo1 = new ArticuloVenta();
//        articulo1.setLinea(linea);
//
//        ArticuloVenta articulo2 = new ArticuloVenta();
//        articulo2.setLinea(linea);
//
//        List<ArticuloVenta> articulos = Arrays.asList(articulo1, articulo2);
//        when(modelService.listar(anyString())).thenReturn(articulos);
//        when(modelService.findPaginated(any(PageRequest.class), anyList())).thenReturn(new PageImpl<>(Arrays.asList(new ArticuloVentaDTO(), new ArticuloVentaDTO())));
//
//        ResponseEntity<Page<ArticuloVentaDTO>> result = controller.getItems("", 0, 10);
//
//        assertEquals(2, result.getBody().getContent().size());
//        verify(modelService, times(1)).listar(anyString());
//        verify(modelService, times(1)).findPaginated(any(PageRequest.class), anyList());
//    }
//
//    @Test
//    void agregar_createsAndReturnsArticuloVenta() {
//        ArticuloVentaDTO dto = new ArticuloVentaDTO();
//        dto.setDenominacion("Test");
//        dto.setLinea(1);
//        ArticuloVenta savedArticulo = new ArticuloVenta();
//        when(lineaService.buscarPorId(anyInt())).thenReturn(new Linea());
//        when(modelService.guardar(any(ArticuloVenta.class))).thenReturn(savedArticulo);
//
//        ArticuloVenta result = controller.agregar(dto);
//
//        assertNotNull(result);
//        verify(lineaService, times(1)).buscarPorId(anyInt());
//        verify(modelService, times(1)).guardar(any(ArticuloVenta.class));
//    }
//
//    @Test
//    void eliminar_marksArticuloVentaAsDeleted() {
//        ArticuloVenta articulo = new ArticuloVenta();
//        when(modelService.buscarPorId(anyInt())).thenReturn(articulo);
//
//        ResponseEntity<ArticuloVenta> result = controller.eliminar(1);
//
//        assertEquals(ArticuloVenta.ESTADO_ELIMINADO, articulo.getEstado());
//        verify(modelService, times(1)).buscarPorId(anyInt());
//        verify(modelService, times(1)).guardar(any(ArticuloVenta.class));
//    }
//
//    @Test
//    void eliminar_throwsExceptionWhenArticuloVentaNotFound() {
//        when(modelService.buscarPorId(anyInt())).thenReturn(null);
//
//        assertThrows(RecursoNoEncontradoExcepcion.class, () -> controller.eliminar(1));
//        verify(modelService, times(1)).buscarPorId(anyInt());
//    }
//
//    @Test
//    void getPorId_returnsArticuloVentaDTO() {
//        Linea linea = new Linea();
//        linea.setId(1);
//
//        ArticuloVenta articulo = new ArticuloVenta();
//        articulo.setLinea(linea); // Set the Linea object
//
//        when(modelService.buscarPorId(anyInt())).thenReturn(articulo);
//
//        ResponseEntity<ArticuloVentaDTO> result = controller.getPorId(1);
//
//        assertNotNull(result.getBody());
//        verify(modelService, times(1)).buscarPorId(anyInt());
//    }
//
//    @Test
//    void getPorId_throwsExceptionWhenArticuloVentaNotFound() {
//        when(modelService.buscarPorId(anyInt())).thenReturn(null);
//
//        assertThrows(RecursoNoEncontradoExcepcion.class, () -> controller.getPorId(1));
//        verify(modelService, times(1)).buscarPorId(anyInt());
//    }
//
//    @Test
//
//    void actualizar_updatesAndReturnsArticuloVenta() {
//        ArticuloVenta articulo = new ArticuloVenta();
//        ArticuloVentaDTO dto = new ArticuloVentaDTO();
//        dto.setDenominacion("Updated");
//        dto.setLinea(1);
//        when(modelService.buscarPorId(anyInt())).thenReturn(articulo);
//        when(lineaService.buscarPorId(anyInt())).thenReturn(new Linea());
//        when(modelService.guardar(any(ArticuloVenta.class))).thenReturn(articulo);
//
//        ResponseEntity<ArticuloVenta> result = controller.actualizar(1, dto);
//
//        assertEquals("Updated", articulo.getDenominacion());
//        verify(modelService, times(1)).buscarPorId(anyInt());
//        verify(lineaService, times(1)).buscarPorId(anyInt());
//        verify(modelService, times(1)).guardar(any(ArticuloVenta.class));
//    }
//
//    @Test
//    void actualizar_throwsExceptionWhenArticuloVentaNotFound() {
//        ArticuloVentaDTO dto = new ArticuloVentaDTO();
//        dto.setDenominacion("Updated");
//        dto.setLinea(1);
//        when(modelService.buscarPorId(anyInt())).thenReturn(null);
//
//        assertThrows(RecursoNoEncontradoExcepcion.class, () -> controller.actualizar(1, dto));
//        verify(modelService, times(1)).buscarPorId(anyInt());
//    }
//}