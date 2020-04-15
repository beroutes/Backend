import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SeguidoresComponentsPage, SeguidoresDeleteDialog, SeguidoresUpdatePage } from './seguidores.page-object';

const expect = chai.expect;

describe('Seguidores e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let seguidoresComponentsPage: SeguidoresComponentsPage;
  let seguidoresUpdatePage: SeguidoresUpdatePage;
  let seguidoresDeleteDialog: SeguidoresDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Seguidores', async () => {
    await navBarPage.goToEntity('seguidores');
    seguidoresComponentsPage = new SeguidoresComponentsPage();
    await browser.wait(ec.visibilityOf(seguidoresComponentsPage.title), 5000);
    expect(await seguidoresComponentsPage.getTitle()).to.eq('Seguidores');
    await browser.wait(ec.or(ec.visibilityOf(seguidoresComponentsPage.entities), ec.visibilityOf(seguidoresComponentsPage.noResult)), 1000);
  });

  it('should load create Seguidores page', async () => {
    await seguidoresComponentsPage.clickOnCreateButton();
    seguidoresUpdatePage = new SeguidoresUpdatePage();
    expect(await seguidoresUpdatePage.getPageTitle()).to.eq('Create or edit a Seguidores');
    await seguidoresUpdatePage.cancel();
  });

  it('should create and save Seguidores', async () => {
    const nbButtonsBeforeCreate = await seguidoresComponentsPage.countDeleteButtons();

    await seguidoresComponentsPage.clickOnCreateButton();

    await promise.all([]);

    await seguidoresUpdatePage.save();
    expect(await seguidoresUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await seguidoresComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Seguidores', async () => {
    const nbButtonsBeforeDelete = await seguidoresComponentsPage.countDeleteButtons();
    await seguidoresComponentsPage.clickOnLastDeleteButton();

    seguidoresDeleteDialog = new SeguidoresDeleteDialog();
    expect(await seguidoresDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Seguidores?');
    await seguidoresDeleteDialog.clickOnConfirmButton();

    expect(await seguidoresComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
