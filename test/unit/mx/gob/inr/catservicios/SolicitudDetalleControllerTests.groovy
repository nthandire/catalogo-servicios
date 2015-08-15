package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(SolicitudDetalleController)
@Mock(SolicitudDetalle)
class SolicitudDetalleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/solicitudDetalle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.solicitudDetalleInstanceList.size() == 0
        assert model.solicitudDetalleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.solicitudDetalleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.solicitudDetalleInstance != null
        assert view == '/solicitudDetalle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/solicitudDetalle/show/1'
        assert controller.flash.message != null
        assert SolicitudDetalle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudDetalle/list'

        populateValidParams(params)
        def solicitudDetalle = new SolicitudDetalle(params)

        assert solicitudDetalle.save() != null

        params.id = solicitudDetalle.id

        def model = controller.show()

        assert model.solicitudDetalleInstance == solicitudDetalle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudDetalle/list'

        populateValidParams(params)
        def solicitudDetalle = new SolicitudDetalle(params)

        assert solicitudDetalle.save() != null

        params.id = solicitudDetalle.id

        def model = controller.edit()

        assert model.solicitudDetalleInstance == solicitudDetalle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitudDetalle/list'

        response.reset()

        populateValidParams(params)
        def solicitudDetalle = new SolicitudDetalle(params)

        assert solicitudDetalle.save() != null

        // test invalid parameters in update
        params.id = solicitudDetalle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/solicitudDetalle/edit"
        assert model.solicitudDetalleInstance != null

        solicitudDetalle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/solicitudDetalle/show/$solicitudDetalle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        solicitudDetalle.clearErrors()

        populateValidParams(params)
        params.id = solicitudDetalle.id
        params.version = -1
        controller.update()

        assert view == "/solicitudDetalle/edit"
        assert model.solicitudDetalleInstance != null
        assert model.solicitudDetalleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/solicitudDetalle/list'

        response.reset()

        populateValidParams(params)
        def solicitudDetalle = new SolicitudDetalle(params)

        assert solicitudDetalle.save() != null
        assert SolicitudDetalle.count() == 1

        params.id = solicitudDetalle.id

        controller.delete()

        assert SolicitudDetalle.count() == 0
        assert SolicitudDetalle.get(solicitudDetalle.id) == null
        assert response.redirectedUrl == '/solicitudDetalle/list'
    }
}
