package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(BitacoraController)
@Mock(Bitacora)
class BitacoraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bitacora/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bitacoraInstanceList.size() == 0
        assert model.bitacoraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bitacoraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bitacoraInstance != null
        assert view == '/bitacora/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bitacora/show/1'
        assert controller.flash.message != null
        assert Bitacora.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bitacora/list'

        populateValidParams(params)
        def bitacora = new Bitacora(params)

        assert bitacora.save() != null

        params.id = bitacora.id

        def model = controller.show()

        assert model.bitacoraInstance == bitacora
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bitacora/list'

        populateValidParams(params)
        def bitacora = new Bitacora(params)

        assert bitacora.save() != null

        params.id = bitacora.id

        def model = controller.edit()

        assert model.bitacoraInstance == bitacora
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bitacora/list'

        response.reset()

        populateValidParams(params)
        def bitacora = new Bitacora(params)

        assert bitacora.save() != null

        // test invalid parameters in update
        params.id = bitacora.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bitacora/edit"
        assert model.bitacoraInstance != null

        bitacora.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bitacora/show/$bitacora.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bitacora.clearErrors()

        populateValidParams(params)
        params.id = bitacora.id
        params.version = -1
        controller.update()

        assert view == "/bitacora/edit"
        assert model.bitacoraInstance != null
        assert model.bitacoraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bitacora/list'

        response.reset()

        populateValidParams(params)
        def bitacora = new Bitacora(params)

        assert bitacora.save() != null
        assert Bitacora.count() == 1

        params.id = bitacora.id

        controller.delete()

        assert Bitacora.count() == 0
        assert Bitacora.get(bitacora.id) == null
        assert response.redirectedUrl == '/bitacora/list'
    }
}
