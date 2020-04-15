import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CategoriasComponentsPage, CategoriasDeleteDialog, CategoriasUpdatePage } from './categorias.page-object';

const expect = chai.expect;

describe('Categorias e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let categoriasComponentsPage: CategoriasComponentsPage;
  let categoriasUpdatePage: CategoriasUpdatePage;
  let categoriasDeleteDialog: CategoriasDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Categorias', async () => {
    await navBarPage.goToEntity('categorias');
    categoriasComponentsPage = new CategoriasComponentsPage();
    await browser.wait(ec.visibilityOf(categoriasComponentsPage.title), 5000);
    expect(await categoriasComponentsPage.getTitle()).to.eq('Categorias');
    await browser.wait(ec.or(ec.visibilityOf(categoriasComponentsPage.entities), ec.visibilityOf(categoriasComponentsPage.noResult)), 1000);
  });

  it('should load create Categorias page', async () => {
    await categoriasComponentsPage.clickOnCreateButton();
    categoriasUpdatePage = new CategoriasUpdatePage();
    expect(await categoriasUpdatePage.getPageTitle()).to.eq('Create or edit a Categorias');
    await categoriasUpdatePage.cancel();
  });

  it('should create and save Categorias', async () => {
    const nbButtonsBeforeCreate = await categoriasComponentsPage.countDeleteButtons();

    await categoriasComponentsPage.clickOnCreateButton();

    await promise.all([categoriasUpdatePage.setNombreInput('nombre')]);

    expect(await categoriasUpdatePage.getNombreInput()).to.eq('nombre', 'Expected Nombre value to be equals to nombre');

    await categoriasUpdatePage.save();
    expect(await categoriasUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await categoriasComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Categorias', async () => {
    const nbButtonsBeforeDelete = await categoriasComponentsPage.countDeleteButtons();
    await categoriasComponentsPage.clickOnLastDeleteButton();

    categoriasDeleteDialog = new CategoriasDeleteDialog();
    expect(await categoriasDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Categorias?');
    await categoriasDeleteDialog.clickOnConfirmButton();

    expect(await categoriasComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
