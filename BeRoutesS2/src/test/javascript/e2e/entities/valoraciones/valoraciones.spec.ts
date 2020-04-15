import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ValoracionesComponentsPage, ValoracionesDeleteDialog, ValoracionesUpdatePage } from './valoraciones.page-object';

const expect = chai.expect;

describe('Valoraciones e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let valoracionesComponentsPage: ValoracionesComponentsPage;
  let valoracionesUpdatePage: ValoracionesUpdatePage;
  let valoracionesDeleteDialog: ValoracionesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Valoraciones', async () => {
    await navBarPage.goToEntity('valoraciones');
    valoracionesComponentsPage = new ValoracionesComponentsPage();
    await browser.wait(ec.visibilityOf(valoracionesComponentsPage.title), 5000);
    expect(await valoracionesComponentsPage.getTitle()).to.eq('Valoraciones');
    await browser.wait(
      ec.or(ec.visibilityOf(valoracionesComponentsPage.entities), ec.visibilityOf(valoracionesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Valoraciones page', async () => {
    await valoracionesComponentsPage.clickOnCreateButton();
    valoracionesUpdatePage = new ValoracionesUpdatePage();
    expect(await valoracionesUpdatePage.getPageTitle()).to.eq('Create or edit a Valoraciones');
    await valoracionesUpdatePage.cancel();
  });

  it('should create and save Valoraciones', async () => {
    const nbButtonsBeforeCreate = await valoracionesComponentsPage.countDeleteButtons();

    await valoracionesComponentsPage.clickOnCreateButton();

    await promise.all([valoracionesUpdatePage.setEstrellasInput('5'), valoracionesUpdatePage.setComentariosInput('comentarios')]);

    expect(await valoracionesUpdatePage.getEstrellasInput()).to.eq('5', 'Expected estrellas value to be equals to 5');
    expect(await valoracionesUpdatePage.getComentariosInput()).to.eq(
      'comentarios',
      'Expected Comentarios value to be equals to comentarios'
    );

    await valoracionesUpdatePage.save();
    expect(await valoracionesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await valoracionesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Valoraciones', async () => {
    const nbButtonsBeforeDelete = await valoracionesComponentsPage.countDeleteButtons();
    await valoracionesComponentsPage.clickOnLastDeleteButton();

    valoracionesDeleteDialog = new ValoracionesDeleteDialog();
    expect(await valoracionesDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Valoraciones?');
    await valoracionesDeleteDialog.clickOnConfirmButton();

    expect(await valoracionesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
