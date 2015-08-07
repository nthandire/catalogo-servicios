package mx.gob.inr.catservicios



import org.junit.*
import grails.test.mixin.*

@TestFor(Cat_servCatController)
@Mock(Cat_servCat)
class Cat_servCatControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cat_servCat/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cat_servCatInstanceList.size() == 0
        assert model.cat_servCatInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cat_servCatInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cat_servCatInstance != null
        assert view == '/cat_servCat/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cat_servCat/show/1'
        assert controller.flash.message != null
        assert Cat_servCat.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servCat/list'

        populateValidParams(params)
        def cat_servCat = new Cat_servCat(params)

        assert cat_servCat.save() != null

        params.id = cat_servCat.id

        def model = controller.show()

        assert model.cat_servCatInstance == cat_servCat
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servCat/list'

        populateValidParams(params)
        def cat_servCat = new Cat_servCat(params)

        assert cat_servCat.save() != null

        params.id = cat_servCat.id

        def model = controller.edit()

        assert model.cat_servCatInstance == cat_servCat
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cat_servCat/list'

        response.reset()

        populateValidParams(params)
        def cat_servCat = new Cat_servCat(params)

        assert cat_servCat.save() != null

        // test invalid parameters in update
        params.id = cat_servCat.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cat_servCat/edit"
        assert model.cat_servCatInstance != null

        cat_servCat.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cat_servCat/show/$cat_servCat.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cat_servCat.clearErrors()

        populateValidParams(params)
        params.id = cat_servCat.id
        params.version = -1
        controller.update()

        assert view == "/cat_servCat/edit"
        assert model.cat_servCatInstance != null
        assert model.cat_servCatInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cat_servCat/list'

        response.reset()

        populateValidParams(params)
        def cat_servCat = new Cat_servCat(params)

        assert cat_servCat.save() != null
        assert Cat_servCat.count() == 1

        params.id = cat_servCat.id

        controller.delete()

        assert Cat_servCat.count() == 0
        assert Cat_servCat.get(cat_servCat.id) == null
        assert response.redirectedUrl == '/cat_servCat/list'
    }
}
