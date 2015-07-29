package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(Cat_bitacoraController)
@Mock(Cat_bitacora)
class Cat_bitacoraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cat_bitacora/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cat_bitacoraInstanceList.size() == 0
        assert model.cat_bitacoraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cat_bitacoraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cat_bitacoraInstance != null
        assert view == '/cat_bitacora/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cat_bitacora/show/1'
        assert controller.flash.message != null
        assert Cat_bitacora.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_bitacora/list'

        populateValidParams(params)
        def cat_bitacora = new Cat_bitacora(params)

        assert cat_bitacora.save() != null

        params.id = cat_bitacora.id

        def model = controller.show()

        assert model.cat_bitacoraInstance == cat_bitacora
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_bitacora/list'

        populateValidParams(params)
        def cat_bitacora = new Cat_bitacora(params)

        assert cat_bitacora.save() != null

        params.id = cat_bitacora.id

        def model = controller.edit()

        assert model.cat_bitacoraInstance == cat_bitacora
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_bitacora/list'

        response.reset()

        populateValidParams(params)
        def cat_bitacora = new Cat_bitacora(params)

        assert cat_bitacora.save() != null

        // test invalid parameters in update
        params.id = cat_bitacora.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cat_bitacora/edit"
        assert model.cat_bitacoraInstance != null

        cat_bitacora.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cat_bitacora/show/$cat_bitacora.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cat_bitacora.clearErrors()

        populateValidParams(params)
        params.id = cat_bitacora.id
        params.version = -1
        controller.update()

        assert view == "/cat_bitacora/edit"
        assert model.cat_bitacoraInstance != null
        assert model.cat_bitacoraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cat_bitacora/list'

        response.reset()

        populateValidParams(params)
        def cat_bitacora = new Cat_bitacora(params)

        assert cat_bitacora.save() != null
        assert Cat_bitacora.count() == 1

        params.id = cat_bitacora.id

        controller.delete()

        assert Cat_bitacora.count() == 0
        assert Cat_bitacora.get(cat_bitacora.id) == null
        assert response.redirectedUrl == '/cat_bitacora/list'
    }
}
