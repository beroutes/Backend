import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CiudadesComponentsPage, CiudadesDeleteDialog, CiudadesUpdatePage } from './ciudades.page-object';

const expect = chai.expect;

describe('Ciudades e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let ciudadesComponentsPage: CiudadesComponentsPage;
  let ciudadesUpdatePage: CiudadesUpdatePage;
  let ciudadesDeleteDialog: CiudadesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Ciudades', async () => {
    await navBarPage.goToEntity('ciudades');
    ciudadesComponentsPage = new CiudadesComponentsPage();
    await browser.wait(ec.visibilityOf(ciudadesComponentsPage.title), 5000);
    expect(await ciudadesComponentsPage.getTitle()).to.eq('Ciudades');
    await browser.wait(ec.or(ec.visibilityOf(ciudadesComponentsPage.entities), ec.visibilityOf(ciudadesComponentsPage.noResult)), 1000);
  });

  it('should load create Ciudades page', async () => {
    await ciudadesComponentsPage.clickOnCreateButton();
    ciudadesUpdatePage = new CiudadesUpdatePage();
    expect(await ciudadesUpdatePage.getPageTitle()).to.eq('Create or edit a Ciudades');
    await ciudadesUpdatePage.cancel();
  });

  it('should create and save Ciudades', async () => {
    const nbButtonsBeforeCreate = await ciudadesComponentsPage.countDeleteButtons();

    await ciudadesComponentsPage.clickOnCreateButton();

    await promise.all([ciudadesUpdatePage.setNombreInput('nombre')]);

    expect(await ciudadesUpdatePage.getNombreInput()).to.eq('nombre', 'Expected Nombre value to be equals to nombre');

    await ciudadesUpdatePage.save();
    expect(await ciudadesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await ciudadesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Ciudades', async () => {
    const nbButtonsBeforeDelete = await ciudadesComponentsPage.countDeleteButtons();
    await ciudadesComponentsPage.clickOnLastDeleteButton();

    ciudadesDeleteDialog = new CiudadesDeleteDialog();
    expect(await ciudadesDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Ciudades?');
    await ciudadesDeleteDialog.clickOnConfirmButton();

    expect(await ciudadesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
