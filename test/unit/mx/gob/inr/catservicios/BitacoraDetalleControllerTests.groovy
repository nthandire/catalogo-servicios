package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(BitacoraDetalleController)
@Mock(BitacoraDetalle)
class BitacoraDetalleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bitacoraDetalle/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bitacoraDetalleInstanceList.size() == 0
        assert model.bitacoraDetalleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bitacoraDetalleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bitacoraDetalleInstance != null
        assert view == '/bitacoraDetalle/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bitacoraDetalle/show/1'
        assert controller.flash.message != null
        assert BitacoraDetalle.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bitacoraDetalle/list'

        populateValidParams(params)
        def bitacoraDetalle = new BitacoraDetalle(params)

        assert bitacoraDetalle.save() != null

        params.id = bitacoraDetalle.id

        def model = controller.show()

        assert model.bitacoraDetalleInstance == bitacoraDetalle
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bitacoraDetalle/list'

        populateValidParams(params)
        def bitacoraDetalle = new BitacoraDetalle(params)

        assert bitacoraDetalle.save() != null

        params.id = bitacoraDetalle.id

        def model = controller.edit()

        assert model.bitacoraDetalleInstance == bitacoraDetalle
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bitacoraDetalle/list'

        response.reset()

        populateValidParams(params)
        def bitacoraDetalle = new BitacoraDetalle(params)

        assert bitacoraDetalle.save() != null

        // test invalid parameters in update
        params.id = bitacoraDetalle.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bitacoraDetalle/edit"
        assert model.bitacoraDetalleInstance != null

        bitacoraDetalle.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bitacoraDetalle/show/$bitacoraDetalle.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bitacoraDetalle.clearErrors()

        populateValidParams(params)
        params.id = bitacoraDetalle.id
        params.version = -1
        controller.update()

        assert view == "/bitacoraDetalle/edit"
        assert model.bitacoraDetalleInstance != null
        assert model.bitacoraDetalleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bitacoraDetalle/list'

        response.reset()

        populateValidParams(params)
        def bitacoraDetalle = new BitacoraDetalle(params)

        assert bitacoraDetalle.save() != null
        assert BitacoraDetalle.count() == 1

        params.id = bitacoraDetalle.id

        controller.delete()

        assert BitacoraDetalle.count() == 0
        assert BitacoraDetalle.get(bitacoraDetalle.id) == null
        assert response.redirectedUrl == '/bitacoraDetalle/list'
    }
}
